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
            firstGrid.setConfig({
                target: $('[data-ax5grid="first-grid"]'),
                columns: [
                    {key: "a", label: "field A"},
                    {key: "b", label: "field B"},
                    {key: "c", label: "numbers C"},
                    {key: "d", label: "field D"},
                    {key: "e", label: "field E"},
                    {key: "f", label: "field F"},
                    {key: "g", label: "field G"},
                    {key: "h", label: "field H"}
                ]
            });

            var list = [];
            for (var a = 0, l = 100; a < l; a++) {
                list.push({a: "A value", b: "B value", c: a % 10, d: "D value", e: "E value", f: "F value", g: "G value"});
            }
            firstGrid.setData(list);
            secondGrid.setConfig({
                target: $('[data-ax5grid="second-grid"]'),
                columns: [
                    {key: "a", label: "field A"},
                    {key: "b", label: "field B"},
                    {key: "c", label: "numbers C"},
                    {key: "d", label: "field D"},
                    {key: "e", label: "field E"},
                    {key: "f", label: "field F"},
                    {key: "g", label: "field G"},
                    {key: "h", label: "field H"}
                ]
            });
            secondGrid.setData(list);

                  // grid control button
                  $('[data-grid-control]').click(function () {
                      switch (this.getAttribute("data-grid-control")) {
                          case "excel-export":
                              secondTabGrid.exportExcel("grid-to-excel.xls");
                              break;
                          case "excel-string":
                              console.log(secondTabGrid.exportExcel());
                              break;
                      }
                  });

                  $(document).on('click','[data-tab-label]', function() {
                        switch (this.getAttribute("data-tab-label")) {
                            case "0":
                                firstGrid.setData(list);
                                break;
                            case "1":
                                secondGrid.setData(list);
                                break;
                        }
                 });
                 $("#tree").fancytree({
                   extensions: ["dnd"],
                   checkbox: true,
                 //			debugLevel: 1,
                   source: {
                     url: "assets/plugins/fancytree/fancytree-master/demo/ajax-tree-plain.json"
                   },
                   activate: function(event, data) {
                   },
                   lazyLoad: function(event, data) {
                     data.result = {url: "assets/plugins/fancytree/fancytree-master/demo/ajax-sub2.json"}
                   },
                   dnd: {
                     preventVoidMoves: true, // Prevent dropping nodes 'before self', etc.
                     preventRecursiveMoves: true, // Prevent dropping nodes on own descendants
                     autoExpandMS: 400,
                     // focusOnClick: true,
                     refreshPositions: true,
                     draggable: {
                       appendTo: "body",  // We don't want to clip the helper inside container
                       // scroll: false,
                       // containment: "parent",  // $("ul.fancytree-container"),
                       // cursorAt: { left: 5 },
                       revert: "invalid"
                       // revert: function(dropped) {
                       // 	return
                       // }
                     },
                     dragStart: function(node, data) {
                       // allow dragging `node`:
                       return true;
                     },
                     dragEnter: function(node, data) {
                        return true;
                     },
                     initHelper: function(node, data) {
                       // Helper was just created: modify markup
                       var helper = data.ui.helper,
                         sourceNodes = data.tree.getSelectedNodes();

                       // Store a list of active + all selected nodes
                       if( !node.isSelected() ) {
                         sourceNodes.unshift(node);
                       }
                       helper.data("sourceNodes", sourceNodes);
                       // Mark selected nodes also as drag source (active node is already)
                       $(".fancytree-active,.fancytree-selected", tree.$container)
                         .addClass("fancytree-drag-source");
                       // Add a counter badge to helper if dragging more than one node
                       if( sourceNodes.length > 1 ) {
                         helper.append($("<span class='fancytree-childcounter'/>")
                           .text("+" + (sourceNodes.length - 1)));
                       }
                       // Prepare an indicator for copy-mode
                       helper.prepend($("<span class='fancytree-dnd-modifier'/>")
                         .text("+").hide());
                     },
                     updateHelper: function(node, data) {
                       // Mouse was moved or key pressed: update helper according to modifiers

                       // NOTE: pressing modifier keys stops dragging in jQueryUI 1.11
                       // http://bugs.jqueryui.com/ticket/14461
                       var event = data.originalEvent,
                         tree = node.tree,
                         copyMode = event.ctrlKey || event.altKey;

                       // Adjust the drop marker icon
                 //					data.dropMarker.toggleClass("fancytree-drop-copy", copyMode);

                       // Show/hide the helper's copy indicator (+)
                       data.ui.helper.find(".fancytree-dnd-modifier").toggle(copyMode);
                       // tree.debug("1", $(".fancytree-active,.fancytree-selected", tree.$container).length)
                       // tree.debug("2", $(".fancytree-active,.fancytree-selected").length)
                       // Dim the source node(s) in move-mode
                       $(".fancytree-drag-source", tree.$container)
                         .toggleClass("fancytree-drag-remove", !copyMode);
                       // data.dropMarker.toggleClass("fancytree-drop-move", !copyMode);
                     },
                     dragDrop: function(node, data) {
                       var sourceNodes = data.ui.helper.data("sourceNodes"),
                         event = data.originalEvent,
                         copyMode = event.ctrlKey || event.altKey;

                       if( copyMode ) {
                         $.each(sourceNodes, function(i, o){
                           o.copyTo(node, data.hitMode, function(n){
                             delete n.key;
                             n.selected = false;
                             n.title = "Copy of " + n.title;
                           });
                         });
                       }else{
                         $.each(sourceNodes, function(i, o){
                           o.moveTo(node, data.hitMode);
                         });
                       }
                     }
                   }
                 });
        }); //ready end



      }); //function end`

    });
});
