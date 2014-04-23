package com.erglesoft.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.sql.*;
import java.util.Arrays;
import java.security.SecureRandom;

import com.erglesoft.dbo.Player;

public class Owasp {
  private final static int ITERATION_NUMBER = 1000;

  public Owasp() {
  }

  /**
   * Authenticates the user with a given login and password
   * If password and/or login is null then always returns false.
   * If the user does not exist in the database returns false.
   * @param con Connection An open connection to a databse
   * @param login String The login of the user
   * @param password String The password of the user
   * @return boolean Returns true if the user is authenticated, false otherwise
   * @throws SQLException If the database is inconsistent or unavailable (
   *           (Two users with the same login, salt or digested password altered etc.)
   * @throws NoSuchAlgorithmException If the algorithm SHA-1 is not supported by the JVM
   */
  public boolean authenticate(Player target, String password) throws SQLException, NoSuchAlgorithmException{
      try {
          boolean userExist = true;
          // INPUT VALIDATION
          if (target==null||password==null){
              // TIME RESISTANT ATTACK
              // Computation time is equal to the time needed by a legitimate user
              userExist = false;
              target=null;
              password="";
          }

          String digest, salt;
          digest = target.getPassword();
          salt = target.getSalt();
          if (digest == null || salt == null) {
              throw new SQLException("Database inconsistant Salt or Digested Password altered");
          }

          byte[] bDigest = base64ToByte(digest);
          byte[] bSalt = base64ToByte(salt);

          // Compute the new DIGEST
          byte[] proposedDigest = getHash(ITERATION_NUMBER, password, bSalt);

          return Arrays.equals(proposedDigest, bDigest) && userExist;
      } catch (IOException ex){
          throw new SQLException("Database inconsistant Salt or Digested Password altered");
      }
  }



  /**
   * Inserts a new user in the database
   * @param con Connection An open connection to a databse
   * @param login String The login of the user
   * @param password String The password of the user
   * @return boolean Returns true if the login and password are ok (not null and length(login)<=100
   * @throws SQLException If the database is unavailable
   * @throws NoSuchAlgorithmException If the algorithm SHA-1 or the SecureRandom is not supported by the JVM
 * @throws UnsupportedEncodingException 
   */
  public boolean createUserPassword(Player target, String password) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException
  {
      if (target!=null&&password!=null&&target.getLogin()!=null&&target.getLogin().length()<=100){
          // Uses a secure Random not a simple Random
          SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
          // Salt generation 64 bits long
          byte[] bSalt = new byte[8];
          random.nextBytes(bSalt);
          // Digest computation
          byte[] bDigest = getHash(ITERATION_NUMBER,password,bSalt);
          String sDigest = byteToBase64(bDigest);
          String sSalt = byteToBase64(bSalt);

          target.setPassword(sDigest);
          target.setSalt(sSalt);
          return true;
      } else {
          return false;
      }
  }


  /**
   * From a password, a number of iterations and a salt,
   * returns the corresponding digest
   * @param iterationNb int The number of iterations of the algorithm
   * @param password String The password to encrypt
   * @param salt byte[] The salt
   * @return byte[] The digested password
   * @throws NoSuchAlgorithmException If the algorithm doesn't exist
   * @throws UnsupportedEncodingException 
   */
  public byte[] getHash(int iterationNb, String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
      MessageDigest digest = MessageDigest.getInstance("SHA-1");
      digest.reset();
      digest.update(salt);
      byte[] input = digest.digest(password.getBytes("UTF-8"));
      for (int i = 0; i < iterationNb; i++) {
          digest.reset();
          input = digest.digest(input);
      }
      return input;
  }

  /**
   * From a base 64 representation, returns the corresponding byte[] 
   * @param data String The base64 representation
   * @return byte[]
   * @throws IOException
   */
  public static byte[] base64ToByte(String data) throws IOException {
      BASE64Decoder decoder = new BASE64Decoder();
      return decoder.decodeBuffer(data);
  }

  /**
   * From a byte[] returns a base 64 representation
   * @param data byte[]
   * @return String
   * @throws IOException
   */
  public static String byteToBase64(byte[] data){
      BASE64Encoder endecoder = new BASE64Encoder();
      return endecoder.encode(data);
  }


}