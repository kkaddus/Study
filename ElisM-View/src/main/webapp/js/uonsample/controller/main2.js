define(function (require) {
    var $ = require('jquery')

    //A fabricated API to show interaction of
    //common and specific pieces.
    $(function () {
      //Load common code that includes config, then load the app logic for this page.
      requirejs(['./common','jquery','polyfiller','ax5core', 'fancytree'], function (common,jquery,polyfiller,ax5core,ax5layout,fancytree) {

        /*************************************************************************
        	(c) 2008-2012 Martin Wendt
         *************************************************************************/

        /*******************************************************************************
         * jQuery.skinswitcher plugin.
         *
         * Change CSS include when combobox selection changes.
         * Copyright (c) 2012 Martin Wendt
         *
         * Usage:
        	$("select#skinswitcher").skinswitcher({
        		base: "../src/",
        		choices: [{name: "XP", value: "xp", href: "skin/ui.fancytree.css"},
        				  {name: "Vista", value: "vista", href: "skin-vista/ui.fancytree.css"},
        				  {name: "Lion", value: "lion", href: "skin-lion/ui.fancytree.css"}
        				  ],
        		init: "lion"
        	});
         */
        /*globals alert, prettyPrint */

        (function( $ ) {
        	var PLUGIN_NAME = "skinswitcher",
        		defaultOptions = {
        			/**RegEx that returns prefix, tag, and suffix of the CSS href.*/
        			// skinPattern: "^(\W/skin-)().css$",
        			// mode: "combo", // {String} mode 'combo' or 'radio'
        			base: "",
        			choices: [],
        			// extraChoices: [],
        			// Events:
        			change: $.noop
        		},
        		methods = {
        			init: function(options) {
        				var i,
        					opts = $.extend({}, defaultOptions, options),
        					hrefs = [],
        					$link = null,
        					initialChoice;
        				// $('').skinswitcher did not match a selector
        				if( !this.length ){
        					return this;
        				}
        				// Attach options to skinswitcher combobox for later access
        				this.data("options", opts);
        				// Find the <link> tag that is used to includes our skin CSS.
        				// Add a class for later access.
        				$.each(opts.choices, function(){
        					hrefs.push(this.href.toLowerCase());
        				});
        				$("head link").each(function(){
        					for(i=0; i<hrefs.length; i++){
        						if(this.href.toLowerCase().indexOf(hrefs[i]) >= 0){
        							$link = $(this);
        							$link.addClass(PLUGIN_NAME);
        							initialChoice = opts.choices[i];
        						}
        					}
        				});
        				if( !$link ){
        					$link = $("link." + PLUGIN_NAME);
        				}
        				if( !$link.length ){
        					$.error("Unable to find <link> tag for skinswitcher. Either set `href` to a known skin url or add a `skinswitcher` class.");
        				}
        				//
        				return this.each(function() {
        					// Add options to dropdown list
        					var $combo = $(this);
        					$combo
        						.empty()
        						.skinswitcher("addChoices", opts.choices)
        						.change(function(event){
        							var choice = $(":selected", this).data("choice");
        							$("link." + PLUGIN_NAME).attr("href", opts.base + choice.href);
        							opts.change(choice);
        						});
        					// Find out initial selection
        					if(opts.init){
        						$combo.val(opts.init).change();
        					}else if (initialChoice){
        						// select combobox value to match current <link> tag
        						// decouple this call to prevent IE6 exception
        						setTimeout(function(){
        							$combo.val(initialChoice.value);
        							opts.change(initialChoice);
        						}, 100);
        					}
        				});
        			},
        			option: function(name, value) {
        				var opts = this.data("options");
        				if(typeof value !== "undefined"){
        					opts[name] = value;
        					return this;
        				}else{
        					return opts[name];
        				}
        			},
        			addChoices: function(choices) {
        				var $combo = $(this);
        				if( $.isPlainObject(choices) ){
        					choices = [ choices ];
        				}
        				$.each(choices, function(i, choice){
        					var $opt = $("<option>", {
        							text: choice.name,
        							value: choice.value
        						}).data("choice", choice);
        					$combo.append($opt);
        				});
        				return this;
        			},
        			change: function(value) {
        				$(this).val(value).change();
        				return this;
        			},
        			reset: function() {
        				$(this).val("").change();
        				return this;
        			}
        		};

        	$.fn[PLUGIN_NAME] = function(method) {
        		// Method calling logic
        		if ( methods[method] ) {
        			return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        		} else if ( typeof method === "object" || ! method ) {
        			return methods.init.apply(this, arguments);
        		} else {
        			$.error("Method " +  method + " does not exist on jQuery." + PLUGIN_NAME);
        		}
        	};
        })( jQuery );


        /**
         * Replacement for $().toggle(func1, func2), which was deprecated with jQuery 1.8
         * and removed in 1.9.;
         * Taken from http://stackoverflow.com/a/4911660/19166
         * By Felix Kling
         */
        (function($) {

        var _gaq = _gaq || [],
        	SAMPLE_BUTTON_DEFAULTS = {
        		id: undefined,
        		label: "Sample",
        		newline: true,
        		code: function(){ alert("not implemented"); }
        	};

        $.fn.clickToggle = function(func1, func2) {
        	var funcs = [func1, func2];
        	this.data("toggleclicked", 0);
        	this.click(function() {
        		var data = $(this).data(),
        			tc = data.toggleclicked;
        		$.proxy(funcs[tc], this)();
        		data.toggleclicked = (tc + 1) % 2;
        	});
        	return this;
        };


        window.addSampleButton = function(options) {
        	var sourceCode,
        		opts = $.extend({}, SAMPLE_BUTTON_DEFAULTS, options),
        		$buttonBar = $("#sampleButtons"),
        		$container = $("<span />", {
        			"class": "sampleButtonContainer"
        		});

        	$("<button />", {
        		id: opts.id,
        		title: opts.tooltip,
        		text: opts.label
        	}).click(function(e){
        		e.preventDefault();
        		opts.code();
        	}).appendTo($container);

        	$("<a />", {
        		text: "Source code",
        		href: "#",
        		"class": "showCode"
        	}).appendTo($container)
        	.click(function(e){
        		try {
        			prettyPrint();
        		} catch (e2) {
        			alert(e2);
        		}
        		var $pre = $container.find("pre");
        		if($pre.is(":visible")){
        			$(this).text("Source code");
        		}else{
        			$(this).text("Hide source");
        		}
        		$pre.toggle("slow");
        		return false;
        	});
        	sourceCode = "" + opts.code;
        	// Remove outer function(){ CODE }
        //    sourceCode = sourceCode.match(/[]\{(.*)\}/);
        	sourceCode = sourceCode.substring(
        		sourceCode.indexOf("{") + 1,
        		sourceCode.lastIndexOf("}"));
        //    sourceCode = $.trim(sourceCode);
        	// Reduce tabs from 8 to 2 characters
        	sourceCode = sourceCode.replace(/\t/g, "  ");
        	// Format code samples

        	$("<pre />", {
        		text: sourceCode,
        		"class": "prettyprint"
        	}).hide().appendTo($container);
        	if(opts.newline){
        		$container.append($("<br />"));
        	}
        	if(opts.header){
        		$("<h5 />", {text: opts.header}).appendTo($("p#sampleButtons"));
        	}
        	if( !$("#sampleButtons").length ){
        		$.error("addSampleButton() needs a container with id #sampleButtons");
        	}
        	$container.appendTo($buttonBar);
        };


        function initCodeSamples() {
        	var info,
        		$source = $("#sourceCode");

        	$("#codeExample").clickToggle(
        		function(){
        			$source.show("fast");
        			if( !this.old ){
        				this.old = $(this).html();
        				$.get(this.href, function(code){
        					// Remove <!-- Start_Exclude [...] End_Exclude --> blocks:
        					code = code.replace(/<!-- Start_Exclude(.|\n|\r)*?End_Exclude -->/gi, "<!-- (Irrelevant source removed.) -->");
        					// Reduce tabs from 8 to 2 characters
        					code = code.replace(/\t/g, "  ");
        					$source.text(code);
        					// Format code samples
        					try {
        						prettyPrint();
        					} catch (e) {
        						alert(e);
        					}
        				}, "html");
        			}
        			$(this).html("Hide source code");
        		},
        		function(){
        			$(this).html(this.old);
        			$source.hide("fast");
        		}
        	);
        	if(jQuery.ui){
        		info = "Fancytree " + jQuery.ui.fancytree.version +
        			", jQuery UI " + jQuery.ui.version +
        			", jQuery " + jQuery.fn.jquery;
        /*
        		info += "\n<br>";
        		info += "document.compatMode: " + document.compatMode + "\n";
        		for(e in jQuery.support){
        			info += "<br>\n" + e + ": " + jQuery.support[e];
        		}
        */
        		$("p.sample-links").after("<p class='version-info'>" + info + "</p>");
        	}
        }

        $(function(){
        	// Log to Google Analytics, when not running locally
        	if ( document.URL.toLowerCase().indexOf("wwwendt.de/") >= 0 ) {
        		_gaq.push(["_setAccount", "UA-316028-1"]);
        		_gaq.push(["_trackPageview"]);

        		(function() {
        			var s, ga = document.createElement("script"); ga.type = "text/javascript"; ga.async = true;
        			ga.src = ("https:" === document.location.protocol ? "https://ssl" : "http://www") + ".google-analytics.com/ga.js";
        			s = document.getElementsByTagName("script")[0]; s.parentNode.insertBefore(ga, s);
        		})();
        	}

        	// Show some elements only, if (not) inside the Example Browser
        	if (top.location === window.location){
        		$(".hideOutsideFS").hide();
        	}else{
        		$(".hideInsideFS").hide();
        	}
        	initCodeSamples();

        	$("select#skinswitcher").skinswitcher({
        		base: "../src/",
        		choices: [{name: "XP", value: "xp", href: "skin-xp/ui.fancytree.css"},
        				  {name: "Vista (classic Dynatree)", value: "vista", href: "skin-vista/ui.fancytree.css"},
        				  {name: "Win7", value: "win7", href: "skin-win7/ui.fancytree.css"},
        				  {name: "Win8", value: "win8", href: "skin-win8/ui.fancytree.css"},
        				  {name: "Win8-N", value: "win8n", href: "skin-win8-n/ui.fancytree.css"},
        				  {name: "Win8 xxl", value: "win8xxl", href: "skin-win8-xxl/ui.fancytree.css"},
        				  {name: "Lion", value: "lion", href: "skin-lion/ui.fancytree.css"}
        				  ],
        		change: function(choice) {
        			// console.log("choice: " + choice.value)
        			$("#connectorsSwitch").toggle(choice.value !== "xp");
        		}
        	}).after($("<label id='connectorsSwitch'><input name='cbConnectors' type='checkbox'>Connectors</label>"));

        	$("input[name=cbConnectors]").on("change", function(e){
        		$(".fancytree-container").toggleClass("fancytree-connectors", $(this).is(":checked"));
        	});
        });

        }(jQuery));




        $(function(){
          var count = 1;
          /*
          TODO: option 'preventTextSelect'?
            Already implemented, but doesn't always work:
              }).on("selectstart" + ns, "span.fancytree-title", function(event){
                // prevent mouse-drags to select text ranges
                // tree.debug("<span title> got event " + event.type);
                event.preventDefault();
          TODO: disable auto-scroll by default:
            seems to have problems to calculate helper position,
            --> see here http://api.jqueryui.com/draggable/#event-drag
              for a possible fix?
            and enabling scrolling would always require custom changes, like
            setting the container height?
          TODO: Revert always flies to top-left corner of container
          */
          // Attach the fancytree widget to an existing <div id="tree"> element
          // and pass the tree options as an argument to the fancytree() function:
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

          $(".droppable").droppable({
            drop: function(event, ui){
              var node = $(ui.helper).data("ftSourceNode"),
                tree = node.tree,
                copyMode = event.ctrlKey || event.altKey,
                sourceNodes = ui.helper.data("sourceNodes");

              if( !copyMode ) {
                $.each(sourceNodes, function(i, o){
                  o.remove();
                });
              }
              $(this).append((copyMode ? "Copied " : "Moved ") + sourceNodes.length + " nodes. ");
            }
          });
          $("#tree2").fancytree({
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

        });



        var documentWidth = $(window).width();
        var documentHeight = $(window).height();

        var resizeTimer;

        $(window).on('resize', function (e) {

            clearTimeout(resizeTimer);
            resizeTimer = setTimeout(function () {

                resizeEndLayout();

            }, 250);

        });

        var resizeEndLayout = function () {
            documentWidth = $(window).width();
            documentHeight = $(window).height();
            $('#mainLayout').css({'height': documentHeight, 'width': '100%'});
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
        $(document.body).ready(function () {
            $('#mainLayout').css({'height': documentHeight, 'width': '100%'});
            $('[data-ax5layout]').ax5layout();

            webshims.polyfill('forms forms-ext');
        });
        $(document).on('change', 'input[type="date"]', function () {
            webshims.polyfill('forms forms-ext');
        });



        $(function(){
        	addSampleButton({
        		label: "Disable",
        		id: "btnDisable",
        		code: function(){
        			if( $("#tree").fancytree("option", "disabled") ){
        				$("#tree").fancytree("enable");
        				$("#btnDisable").text("Disable");
        			}else{
        				$("#tree").fancytree("disable");
        				$("#btnDisable").text("Enable");
        			}

        		}
        	});
        	addSampleButton({
        		label: "Expand all",
        		newline: false,
        		code: function(){
        			$("#tree").fancytree("getRootNode").visit(function(node){
        				node.setExpanded(true);
        			});
        		}
        	});
        	addSampleButton({
        		label: "Collapse all",
        		newline: false,
        		code: function(){
        			$("#tree").fancytree("getRootNode").visit(function(node){
        				node.setExpanded(false);
        			});
        		}
        	});
        	addSampleButton({
        		label: "Toggle expand",
        		code: function(){
        			$("#tree").fancytree("getRootNode").visit(function(node){
        				node.toggleExpanded();
        			});
        		}
        	});
        	addSampleButton({
        		label: "tree.getActiveNode()",
        		newline: false,
        		code: function(){
        			var node = $("#tree").fancytree("getActiveNode");
        			if( node ){
        				alert("Currently active: " + node.title);
        			}else{
        				alert("No active node.");
        			}
        		}
        	});
        	addSampleButton({
        		label: "tree.toDict()",
        		code: function(){
        			// Convert the whole tree into an dictionary
        			var tree = $("#tree").fancytree("getTree");
        			var d = tree.toDict(true);
        			alert(JSON.stringify(d));
        		}
        	});
        	addSampleButton({
        		label: "activateKey('id4.3.2')",
        		code: function(){
        			$("#tree").fancytree("getTree").activateKey("id4.3.2");
        			// also possible:
        //	              $("#tree").fancytree("getTree").getNodeByKey("id4.3.2").setActive();
        		}
        	});
        	addSampleButton({
        		label: "setTitle()",
        		code: function(){
        			var node = $("#tree").fancytree("getActiveNode");
        			if( !node ) return;
        			node.setTitle(node.title + ", " + new Date());
        			// this is a shortcut for
        			// node.fromDict({title: data.node.title + new Date()});
        		}
        	});
        	addSampleButton({
        		label: "Sort tree",
        		newline: false,
        		code: function(){
        			var node = $("#tree").fancytree("getRootNode");
        			node.sortChildren(null, true);
        		}
        	});
        	addSampleButton({
        		label: "Sort active banch",
        		code: function(){
        			var node = $("#tree").fancytree("getActiveNode");
        			// Custom compare function (optional) that sorts case insensitive
        			var cmp = function(a, b) {
        				a = a.title.toLowerCase();
        				b = b.title.toLowerCase();
        				return a > b ? 1 : a < b ? -1 : 0;
        			};
        			node.sortChildren(cmp, false);
        		}
        	});
        	addSampleButton({
        		header: "Create nodes",
        		tooltip: "Use node.addChildren() with single objects",
        		label: "Add single nodes",
        		newline: false,
        		code: function(){
        			// Sample: add an hierarchic branch using code.
        			// This is how we would add tree nodes programatically
        			var rootNode = $("#tree").fancytree("getRootNode");
        			var childNode = rootNode.addChildren({
        				title: "Programatically addded nodes",
        				tooltip: "This folder and all child nodes were added programmatically.",
        				folder: true
        			});
        			childNode.addChildren({
        				title: "Document using a custom icon",
        				icon: "customdoc1.gif"
        			});
        		}
        	});
        	addSampleButton({
        		tooltip: "Use node.appendSibling()",
        		label: "Apppend a sibling node",
        		newline: false,
        		code: function(){
        			var tree = $("#tree").fancytree("getTree"),
        				node = tree.getActiveNode(),
        				newData = {title: "New Node"},
        				newSibling = node.appendSibling(newData);
        		}
        	});
        	addSampleButton({
        		label: "ROOT.addChildren()",
        		tooltip: "Use node.addChildren() with recursive arrays",
        		code: function(){
        			// Sample: add an hierarchic branch using an array
        			var obj = [
        				{ title: "Lazy node 1", lazy: true },
        				{ title: "Lazy node 2", lazy: true },
        				{ title: "Folder node 3", folder: true,
        					children: [
        						{ title: "node 3.1" },
        						{ title: "node 3.2",
        							children: [
        								{ title: "node 3.2.1" },
        								{ title: "node 3.2.2",
        									children: [
        										{ title: "node 3.2.2.1" }
        									]
        								}
        							]
        						}
        					]
        				}
        			];
        			$("#tree").fancytree("getRootNode").addChildren(obj);
        		}
        	});
        	addSampleButton({
        		label: "node.fromDict()",
        		code: function(){
        			var node = $("#tree").fancytree("getActiveNode");
        			if( !node ) return;
        			// Set node data and - optionally - replace children
        			node.fromDict({
        				title: node.title + new Date(),
        				children: [{title: "t1"}, {title: "t2"}]
        			});
        		}
        	});
        	CLIPBOARD = null;
        	addSampleButton({
        		label: "Clipboard = node.toDict()",
        		newline: false,
        		code: function(){
        			// Convert active node (and descendants) to a dictionary and store
        			// in
        			var node = $("#tree").fancytree("getActiveNode");
        			var d = node.toDict(true, function(dict){
        				// Remove keys, so they will be re-generated when this dict is
        				// passed to addChildren()
        				delete dict.key;
        			});
        			// Store in a globael variable
        			CLIPBOARD = d;
        			alert("CLIPBOARD = " + JSON.stringify(d));
        		}
        	});
        	addSampleButton({
        		label: "node.fromDict(Clipboard)",
        		code: function(){
        			var node = $("#tree").fancytree("getActiveNode");
        			if( !node ) return;
        			// Set node data and - optionally - replace children
        			node.fromDict(CLIPBOARD);
        		}
        	});
        	addSampleButton({
        		label: "Remove selected nodes (but keep children)",
        		newline: true,
        		code: function(){
        			var tree = $("#tree").fancytree("getTree"),
        				selNodes = tree.getSelectedNodes();

        			selNodes.forEach(function(node) {
        				while( node.hasChildren() ) {
        					node.getFirstChild().moveTo(node.parent, "child");
        				}
        				node.remove();
        			});
        		}
        	});
        });
      }); //function end`

    });
});
