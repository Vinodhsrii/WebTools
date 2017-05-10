$('.dropdown-toggle').dropdown();
$('#divNewNotifications li > a').click(function(){
    $('#text').text($(this).html());
});