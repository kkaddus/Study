define(function (require) {
    var $ = require('jquery')

    //A fabricated API to show interaction of
    //common and specific pieces.
    $(function () {
      //Load common code that includes config, then load the app logic for this page.
      requirejs(['./common','jquery','polyfiller','ax5core'], function (common,jquery,polyfiller,ax5core,ax5layout) {

        var firstGrid = new ax5.ui.grid();

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

            var secondTabGrid = new ax5.ui.grid({
                      target: $('[data-ax5grid="secondTab-grid"]'),
                      columns: [
                          {
                              key: "a",
                              label: "field A",
                              width: 80,
                              styleClass: function () {
                                  return "ABC";
                              },
                              enableFilter: true,
                              align: "center"
                          },
                          {key: "b", label: "field  B", align: "center"},
                          {
                              key: undefined, label: "field C", columns: [
                              {key: "price", label: "price", formatter: "money", align: "right"},
                              {key: "amount", label: "amount", formatter: "money", align: "right"},
                              {
                                  key: "cost", label: "cost", align: "right", formatter: function () {
                                  return ax5.util.number(this.item.price * this.item.amount, {"money": true});
                              }
                              }
                          ]
                          },
                          {key: "saleDt", label: "saleDt", align: "center"},
                          {key: "customer", label: "customer"},
                          {key: "userType", label: "userType"}
                      ],
                      body: {
                          grouping: {
                              by: ["a"],
                              columns: [
                                  {
                                      label: function () {
                                          return this.groupBy.labels.join(", ") + " SUM";
                                      }, colspan: 2, align: "center"
                                  },
                                  {key: "price", collector: "avg", formatter: "money", align: "right"},
                                  {key: "amount", collector: "sum", formatter: "money", align: "right"},
                                  {
                                      key: "cost", collector: function () {
                                      var value = 0;
                                      this.list.forEach(function (n) {
                                          if (!n.__isGrouping) value += (n.price * n.amount);
                                      });
                                      return ax5.util.number(value, {"money": 1});
                                  }, align: "right"
                                  }
                              ]
                          }
                      },
                      footSum: [
                          [
                              {label: "SUMMARY", colspan: 2, align: "center"},
                              {key: "price", collector: "avg", formatter: "money", align: "right"},
                              {key: "amount", collector: "sum", formatter: "money", align: "right"},
                              {
                                  key: "cost", collector: function () {
                                  var value = 0;
                                  this.list.forEach(function (n) {
                                      if (!n.__isGrouping) value += (n.price * n.amount);
                                  });
                                  return ax5.util.number(value, {"money": 1});
                              }, align: "right"
                              }
                          ]]
                  });

                  var sampleData = [
                      {a: "0011", b: "A01", price: 1000, amount: 12, saleDt: "2016-08-29", customer: "장기영", userType: "M"},
                      {a: "0011", b: "A02", price: 1100, amount: 11, saleDt: "2016-08-28", customer: "장서우", userType: "D"},
                      {a: "0011", b: "A03", price: 1200, amount: 10, saleDt: "2016-08-27", customer: "이영희", userType: "W"},
                      {a: "A", b: "A01", price: 1000, amount: 12, saleDt: "2016-08-29", customer: "장기영", userType: "M"},
                      {a: "A", b: "A02", price: 1100, amount: 11, saleDt: "2016-08-28", customer: "장서우", userType: "D"},
                      {a: "A", b: "A03", price: 1200, amount: 10, saleDt: "2016-08-27", customer: "이영희", userType: "W"},
                      {a: "B", b: "B01", price: 1300, amount: 8, saleDt: "2016-08-25", customer: "황인서", userType: "M"},
                      {a: "B", b: "B02", price: 1400, amount: 5, saleDt: "2016-08-29", customer: "황세진", userType: "S"},
                      {a: "B", b: "B03", price: 1500, amount: 2, saleDt: "2016-08-26", customer: "이서연", userType: "W"}
                  ];


                  secondTabGrid.setData(sampleData);
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
                                secondTabGrid.setData(sampleData);
                                break;
                        }
                 });

        }); //ready end



      }); //function end`

    });
});
