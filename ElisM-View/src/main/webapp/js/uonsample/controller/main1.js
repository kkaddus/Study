define(function(require) {
    var $ = require('jquery')

    //A fabricated API to show interaction of
    //common and specific pieces.
    $(function() {
        //Load common code that includes config, then load the app logic for this page.
        requirejs(['./common', 'jquery', 'polyfiller', 'ax5core', ], function(common, jquery, polyfiller, ax5core, ax5layout) {
            var documentWidth = $(window).width();
            var documentHeight = $(window).height();

            var resizeTimer;
            (function($) {
                var origAppend = $.fn.append;

                $.fn.append = function() {
                    return origAppend.apply(this, arguments).trigger("append");
                };
            })(jQuery);

            //  $("div").bind("append", function() { alert('Hello, world!'); });

            //$("div").append("<span>");

            $('#dynamicInputBtn').on('click', function() {
                $('#dynamicInput').append('<div><input type="date" class="form-control"> ie & firefox에서 동작하지 않습니다.</div>');
                $('#dynamicInput').appendPolyfill('<div><input type="date" class="form-control"> 모든 브라우저에서 동작합니다</div>');
            });
            $(window).on('resize', function(e) {
                clearTimeout(resizeTimer);
                resizeTimer = setTimeout(function() {
                    resizeEndLayout();
                }, 250);

            });

            var resizeEndLayout = function() {
                documentWidth = $(window).width();
                documentHeight = $(window).height();
                $('#mainLayout').css({
                    'height': documentHeight,
                    'width': '100%'
                });
                $('[data-ax5layout]').ax5layout("reset");
            };
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
            $(document.body).ready(function() {
                $('#mainLayout').css({
                    'height': documentHeight,
                    'width': '100%'
                });
                $('[data-ax5layout]').ax5layout();
                $('[data-toggle="tooltip"]').tooltip();
                webshims.polyfill('forms forms-ext');
                var mask = new ax5.ui.mask();
                var toast = new ax5.ui.toast();
                var modal = new ax5.ui.modal({
                    theme: "default",
                    header: {
                        title: '<i class="fa fa-arrows" aria-hidden="true"></i> Drag Me !!',
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
                var modalDefault = new ax5.ui.modal({ // 모달 기본세팅
                    width: 700,
                    height: 500,
                    position: {
                        left: "center",
                        top: 10
                    },
                    zIndex: 10060,
                    header: {
                        title: '<i class="fa fa-arrows" aria-hidden="true"></i> Drag Me !!',
                        btns: {
                            minimize: {
                                label: '<i class="fa fa-minus-circle" aria-hidden="true"></i>',
                                onClick: function() {
                                    modalDefault.minimize();
                                }
                            },
                            restore: {
                                label: '<i class="fa fa-plus-circle" aria-hidden="true"></i>',
                                onClick: function() {
                                    modalDefault.restore();
                                }
                            },
                            close: {
                                label: '<i class="fa fa-times-circle" aria-hidden="true"></i>',
                                onClick: function() {
                                    modalDefault.close();
                                }
                            }
                        }
                    },
                    iframe: {
                        method: "get",
                        url: "tree.html",
                        param: "callBack=modalCallBack"
                    },
                    disableResize: false,
                    closeToEsc: true,
                    animateTime: 250,
                    onStateChanged: function() {

                    },
                    zIndex: 10060
                });

                $(document).on('click', '#modal-open2', function() {

                    modalDefault.open(function() {
                        toast.push("modal opened");
                    });
                });
                $(document).on('click', '#modal-open2', function() {
                    modalDefault.open(function() {
                        toast.push("modal opened");
                    });
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
                        var btn = jQuery(
                            '<button class="btn btn-default" type="button" id="modal-open2" style="margin-top: 100px;">' +
                            'Modal open2</button>');
                        this.$["body-frame"].append(btn);
                    });

                });
                var dialog = new ax5.ui.dialog();
               var mask = new ax5.ui.mask();

               dialog.setConfig({
                   title: 'Title',
                   onStateChanged: function () {
                       if (this.state === "open") {
                           mask.open();
                       }
                       else if (this.state === "close") {
                           mask.close();
                       }
                   }
               });

               $('#alert-open-with-mask').click(function () {
                   dialog.alert('Alert message');
               });
               $('#alert-open-with-danger-mask').click(function () {
                   dialog.alert({
                       msg: 'Alert message',
                       onStateChanged: function () {
                           if (this.state === "open") {
                               mask.open({theme: 'danger'});
                           }
                           else if (this.state === "close") {
                               mask.close();
                           }
                       }
                   });
               });
               var confirmDialog = new ax5.ui.dialog();
               confirmDialog.setConfig({
                   title: "XXX",
                   theme: "danger"
               });

               $('#confirm-open').click(function () {
                   confirmDialog.confirm({
                       title: "Confirm Title",
                       msg: 'Confirm message'
                   }, function(){
                       if(this.key == "ok"){
                           alert('OK');
                       }
                       else if(this.key == "cancel"){
                           alert('CANCEL');
                       }
                   });
               });
    }); //document ready
            $(document).on('change', 'input[type="date"]', function() {
                webshims.polyfill('forms forms-ext');
            });

            $(function() {
                var myToast = new ax5.ui.toast();
                var myModel = new ax5.ui.binder();

                myModel.setModel({
                    email: "tom@axisj.com",
                    password: "12345",
                    select: "B",
                    useYn: "Y",
                    sex: "F"
                }, $(document["binder-form"]));

                $('[data-btn]').click(function() {
                    var act = this.getAttribute("data-btn");
                    switch (act) {
                        case "change-model":
                            myModel.setModel({
                                email: "brant@axisj.com",
                                password: "9999",
                                select: "A",
                                useYn: "N",
                                sex: "M"
                            });
                            break;

                        case "get-model":
                            var data = myModel.get();
                            console.log(data);
                            myToast.confirm(JSON.stringify(data));
                            break;
                    }
                });
            });
        });
    });
});
