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