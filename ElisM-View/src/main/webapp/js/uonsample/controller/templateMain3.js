define(function (require) {
    var $ = require('jquery')

    //A fabricated API to show interaction of
    //common and specific pieces.
    $(function () {
      //Load common code that includes config, then load the app logic for this page.
      requirejs(['./common','jquery','polyfiller','ax5core','fancytree'], function (common,jquery,polyfiller,ax5core,fancytree) {

        var firstGrid = new ax5.ui.grid();
        var secondGrid = new ax5.ui.grid();
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


        }); //ready end



      }); //function end`

    });
});
