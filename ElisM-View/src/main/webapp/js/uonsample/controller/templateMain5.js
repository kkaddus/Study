define(function (require) {
    var $ = require('jquery')

    //A fabricated API to show interaction of
    //common and specific pieces.
    $(function () {
      //Load common code that includes config, then load the app logic for this page.
      requirejs(['./common','jquery','polyfiller','ax5core','fullcalendar'], function (common,jquery,polyfiller,ax5core,fullcalendar) {

        var firstGrid = new ax5.ui.grid();
        var secondGrid = new ax5.ui.grid();
        var modalContent = $('#writeSample');
        $(document.body).ready(function () {
            $('[data-ax5layout]').ax5layout();
            webshim.setOptions("forms-ext", {
                "widgets": {
                    "startView": 2,
                    "minView": 0,
                    "inlinePicker": false,
                    "size": 1,
                    "splitInput": false,
                    "yearSelect": false,
                    "monthSelect": false,
                    "daySelect": false,
                    "noChangeDismiss": false,
                    "openOnFocus": false,
                    "buttonOnly": false,
                    "classes": "",
                    "popover": {
                        //popover options
                    },
                    "calculateWidth": true,
                    "animate": true,
                    "toFixed": 0,
                    "onlyFixFloat": false
                }
            });
            $('#calendar').fullCalendar({
        			header: {
        				left: 'prev,next today',
        				center: 'title',
        				right: 'listDay,listWeek,month'
        			},

        			// customize the button names,
        			// otherwise they'd all just say "list"
        			views: {
        				listDay: { buttonText: 'list day' },
        				listWeek: { buttonText: 'list week' }
        			},

        			defaultView: 'month',
        			defaultDate: '2017-02-12',
        			navLinks: true, // can click day/week names to navigate views
        			editable: true,
        			eventLimit: true, // allow "more" link when too many events
        			events: [
        				{
        					title: 'All Day Event',
        					start: '2017-02-01'
        				},
        				{
        					title: 'Long Event',
        					start: '2017-02-07',
        					end: '2017-02-10'
        				},
        				{
        					id: 999,
        					title: 'Repeating Event',
        					start: '2017-02-09T16:00:00'
        				},
        				{
        					id: 999,
        					title: 'Repeating Event',
        					start: '2017-02-16T16:00:00'
        				},
        				{
        					title: 'Conference',
        					start: '2017-02-11',
        					end: '2017-02-13'
        				},
        				{
        					title: 'Meeting',
        					start: '2017-02-12T10:30:00',
        					end: '2017-02-12T12:30:00'
        				},
        				{
        					title: 'Lunch',
        					start: '2017-02-12T12:00:00'
        				},
        				{
        					title: 'Meeting',
        					start: '2017-02-12T14:30:00'
        				},
        				{
        					title: 'Happy Hour',
        					start: '2017-02-12T17:30:00'
        				},
        				{
        					title: 'Dinner',
        					start: '2017-02-12T20:00:00'
        				},
        				{
        					title: 'Birthday Party',
        					start: '2017-02-13T07:00:00'
        				},
        				{
        					title: 'Click for Google',
        					url: 'http://google.com/',
        					start: '2017-02-28'
        				}
        			]
        		});
            var mask = new ax5.ui.mask();
            var toast = new ax5.ui.toast();
            var modal = new ax5.ui.modal({
                theme: "default",
                width: 700,
                height: 420,
                header: {
                    title: '<i class="fa fa-pencil" aria-hidden="true"></i> 일정등록',
                    btns: {
                        minimize: {
                            label: '<i class="fa fa-minus-circle" aria-hidden="true"></i>',
                            onClick: function() {
                                modal.minimize();
                            }
                        },
                        restore: {
                            label: '<i class="fa fa-plus-circle" aria-hidden="true"></i>',
                            onClick: function() {
                                modal.restore();
                            }
                        },
                        close: {
                            label: '<i class="fa fa-times-circle" aria-hidden="true"></i>',
                            onClick: function() {
                                modal.close();
                            }
                        }
                    }
                },
            });
            modal.setConfig({
                onStateChanged: function() {
                    // mask
                    if (this.state === "open") {
                        mask.open();
                    } else if (this.state === "close") {
                        mask.close();
                    }
                }
            });
            $('#modal-open').click(function() {
                modal.open({}, function() {

                    this.$["body-frame"].append(modalContent);
                    this.$["body-frame"].find('#writeSample').css('display','block');
                });

            });

        }); //ready end




      }); //function end`

    });
});
