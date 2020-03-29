var clndr = {};

$( function() {

  // PARDON ME while I do a little magic to keep these events relevant for the rest of time...
  var currentMonth = moment().format('YYYY-MM');
  var nextMonth    = moment().add('month', 1).format('YYYY-MM');

 var events = [
//    { date: currentMonth + '-' + '19', title: 'Cat Frisbee', location: 'Jefferson Park' },
//    { date: currentMonth + '-' + '23', title: 'Kitten Demonstration', location: 'Center for Beautiful Cats' },
//    { date: nextMonth + '-' + '07',    title: 'Small Cat Photo Session', location: 'Center for Cat Photography' }
 ];

  clndr = $('#full-clndr').clndr({
    template: $('#full-clndr-template').html(),
    events: [],
    clickEvents: {
          click: function(target) {
            if(target.events.length) {
              var daysContainer = $('#full-clndr').find('.clndr-grid');
              var eventContainer = $('#full-clndr').find('.event-listing')
              daysContainer.toggleClass('show-events', true);
              eventContainer.toggleClass('show-events', true);
              $('#full-clndr').find('.close').click( function() {
                daysContainer.toggleClass('show-events', false);
                eventContainer.toggleClass('show-events', false);
              });
            }
          }
        },
    forceSixRows: true
  });




});