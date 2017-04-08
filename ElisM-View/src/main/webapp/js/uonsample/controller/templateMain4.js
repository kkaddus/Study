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
            $("#tree").fancytree({
                checkbox: true,
                titlesTabbable: true,     // Add all node titles to TAB chain
                quicksearch: true,        // Jump to nodes when pressing first character
                // source: SOURCE,
                source: { url: "assets/plugins/fancytree/fancytree-master/demo/ajax-tree-products.json"},
                extensions: ["edit", "dnd", "table", "gridnav"],
                dnd: {
                  preventVoidMoves: true,
                  preventRecursiveMoves: true,
                  autoExpandMS: 400,
                  dragStart: function(node, data) {
                    return true;
                  },
                  dragEnter: function(node, data) {
                    // return ["before", "after"];
                    return true;
                  },
                  dragDrop: function(node, data) {
                    data.otherNode.moveTo(node, data.hitMode);
                  }
                },
                edit: {
                  triggerStart: ["f2", "shift+click", "mac+enter"],
                  close: function(event, data) {
                    if( data.save && data.isNew ){
                      // Quick-enter: add new nodes until we hit [enter] on an empty title
                      $("#tree").trigger("nodeCommand", {cmd: "addSibling"});
                    }
                  }
                },
                table: {
                  indentation: 20,
                  nodeColumnIdx: 2,
                  checkboxColumnIdx: 0
                },
                gridnav: {
                  autofocusInput: false,
                  handleCursorKeys: true
                },

                lazyLoad: function(event, data) {
                  data.result = {url: "assets/plugins/fancytree/fancytree-master/demo/ajax-sub2.json"};
                },
                createNode: function(event, data) {
                  var node = data.node,
                    $tdList = $(node.tr).find(">td");

                  // Span the remaining columns if it's a folder.
                  // We can do this in createNode instead of renderColumns, because
                  // the `isFolder` status is unlikely to change later
                  if( node.isFolder() ) {
                    $tdList.eq(2)
                      .prop("colspan", 6)
                      .nextAll().remove();
                  }
                },
                renderColumns: function(event, data) {
                  var node = data.node,
                    $tdList = $(node.tr).find(">td");

                  // (Index #0 is rendered by fancytree by adding the checkbox)
                  // Set column #1 info from node data:
                  $tdList.eq(1).text(node.getIndexHier());
                  // (Index #2 is rendered by fancytree)
                  // Set column #3 info from node data:
                  $tdList.eq(3).find("input").val(node.key);
                  $tdList.eq(4).find("input").val(node.data.foo);

                  // Static markup (more efficiently defined as html row template):
                  // $tdList.eq(3).html("<input type='input' value='" + "" + "'>");
                  // ...
                }
              });

        }); //ready end



      }); //function end`

    });
});
