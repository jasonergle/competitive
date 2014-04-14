CREATE TABLE `game` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `type` varchar(20) NOT NULL,
  `uses_teams` tinyint(4) NOT NULL default '0',
  `max_players` smallint(6) default NULL,
  `min_players` smallint(6) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `type_UNIQUE` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE `league` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `abbr` varchar(10) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `create_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `creator_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `group_owner_to_player_fk_idx` (`owner_id`),
  KEY `group_creator_to_player_fk_idx` (`creator_id`),
  CONSTRAINT `group_creator_to_player_fk` FOREIGN KEY (`creator_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `group_owner_to_player_fk` FOREIGN KEY (`owner_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `league_games` (
  `league_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  PRIMARY KEY  (`league_id`,`game_id`),
  KEY `group_games_to_game_fk_idx` (`game_id`),
  CONSTRAINT `league_games_to_league_fk` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `league_games_to_game_fk` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `league_players` (
  `league_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  PRIMARY KEY  (`league_id`,`player_id`),
  KEY `group_players_to_player_fk_idx` (`player_id`),
  CONSTRAINT `league_players_to_player_fk` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `league_players_to_league_fk` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `player` (
  `id` int(11) NOT NULL auto_increment,
  `first_name` varchar(255) default NULL,
  `last_name` varchar(255) default NULL,
  `login` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `active_flag` tinyint(4) NOT NULL default '1',
  `current_league` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `player_current_group_to_group_fk_idx` (`current_league`),
  CONSTRAINT `player_current_league_to_league_fk` FOREIGN KEY (`current_league`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

CREATE TABLE `player_match` (
  `id` int(11) NOT NULL auto_increment,
  `game_id` int(11) NOT NULL,
  `winner_id` int(11) NOT NULL,
  `loser_id` int(11) NOT NULL,
  `winner_score` int(11) NOT NULL,
  `loser_score` int(11) NOT NULL,
  `is_complete` tinyint(4) NOT NULL default '1',
  `title` varchar(45) default NULL,
  `match_date` timestamp NULL default NULL,
  `create_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `creator_id` int(11) NOT NULL,
  `league_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `match_creator_to_player_fk_idx` (`game_id`),
  KEY `match_to_group_fk_idx` (`league_id`),
  KEY `match_player_winner_to_player_fk_idx` (`winner_id`),
  KEY `match_player_loser_to_player_fk_idx` (`loser_id`),
  KEY `match_player_creator_to_player_fk_idx` (`creator_id`),
  CONSTRAINT `match_player_to_league_fk` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `match_player_creator_to_player_fk` FOREIGN KEY (`creator_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `match_player_loser_to_player_fk` FOREIGN KEY (`loser_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `match_player_to_game_fk` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `match_player_winner_to_player_fk` FOREIGN KEY (`winner_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `team` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `league_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `creator_id` int(11) NOT NULL,
  `create_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `captain_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `team_to_group_idx` (`league_id`),
  KEY `team_to_game_fk_idx` (`game_id`),
  KEY `team_creator_to_player_fk_idx` (`creator_id`),
  KEY `team_captain_id_to_player_fk_idx` (`captain_id`),
  CONSTRAINT `team_to_league_fk` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `team_captain_id_to_player_fk` FOREIGN KEY (`captain_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `team_creator_to_player_fk` FOREIGN KEY (`creator_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `team_to_game_fk` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `team_match` (
  `id` int(11) NOT NULL auto_increment,
  `game_id` int(11) NOT NULL,
  `winner_id` int(11) NOT NULL,
  `loser_id` int(11) NOT NULL,
  `winner_score` int(11) NOT NULL,
  `loser_score` int(11) NOT NULL,
  `is_complete` tinyint(4) NOT NULL default '1',
  `title` varchar(45) default NULL,
  `match_date` timestamp NULL default NULL,
  `create_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `creator_id` int(11) NOT NULL,
  `league_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `match_team_to_game_fk_idx` (`game_id`),
  KEY `match_team_winner_to_team_fk_idx` (`winner_id`),
  KEY `match_team_loser_to_team_fk_idx` (`loser_id`),
  KEY `match_team_creator_to_player_fk_idx` (`creator_id`),
  KEY `match_team_to_league_fk_idx` (`league_id`),
  CONSTRAINT `match_team_to_league_fk` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `match_team_creator_to_player_fk` FOREIGN KEY (`creator_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `match_team_loser_to_team_fk` FOREIGN KEY (`loser_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `match_team_to_game_fk` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `match_team_winner_to_team_fk` FOREIGN KEY (`winner_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `team_players` (
  `team_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  PRIMARY KEY  (`team_id`,`player_id`),
  KEY `team_players_to_player_fk_idx` (`player_id`),
  CONSTRAINT `team_players_to_team_fk` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `team_players_to_player_fk` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
