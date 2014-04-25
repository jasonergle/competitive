$.erglesoft = {};

$.fn.sort_select_box = function(){
    var my_options = $("#" + this.attr('id') + ' option');
    my_options.sort(function(a,b) {
        if (a.text > b.text) return 1;
        else if (a.text < b.text) return -1;
        else return 0;
    });
    $(this).empty().append( my_options );
    // clearing any selections
    $("#"+this.attr('id')+" option").attr('selected', false);
   return my_options;
};

(function($){

	/**
	 * Add this to a select input group to make them disable each other's
	 * selected value.
	 */
	function makeUniqueChoice(el, options) {

		//Defaults:
		this.defaults = {
			defaultStringSetting: 'Hello World'
			, selectorGroup: null
		};

		//Extending options:
		this.opts = $.extend({}, this.defaults, options);

		//Privates:
		this.$el = $(el);
	}

	// Separate functionality from object creation
	makeUniqueChoice.prototype = {

		init: function() {
			var _this = this;
			//Add our own lookup (TODO: make unique)
			this.$el.addClass('xxx');
			this.$el.on('change', function() {
				var player = $(this).val()
					, widget = $(this).data('makeUniqueChoice');
				widget.DisableOtherGuys();
			});
		},

		//Disable the chosen value in other selects'
		DisableOtherGuys : function() {
				var $inputs = this.opts.selectorGroup
					, $theSelect = this.$el
					, playerVal = this.$el.val();

				// Reset the other box if already selected this value.
				$inputs.not($theSelect).each(function(){
					if ($(this).find(":selected").val() === playerVal) {
						$(this).val('-1');
					}
				});
				
				// Disable the chosen player from second player options
				$inputs.not($theSelect).each(function(){
					$(this)
					.find('option[value=' + playerVal + ']')
					.prop("disabled", true);
				});
			}
	};

	// The actual plugin
	$.fn.makeUniqueChoice = function(options) {
		if(this.length) {
			// Capture our selected group here if not passed
			// Used to reference our other select inputs
			options = $.extend({}, {selectorGroup: this}, options);

			return this.each(function() {
				var me = new makeUniqueChoice(this, options);
				me.init();
				$(this).data('makeUniqueChoice', me);
			});
		}
	};
})(jQuery);
