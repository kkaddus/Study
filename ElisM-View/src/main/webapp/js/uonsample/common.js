//The build will inline common dependencies into this file.

//For any third party dependencies, like jQuery, place them in the lib folder.

//Configure loading modules from the lib directory,
//except for 'app' ones, which are in a sibling
//directory.

requirejs.config({
  baseUrl: "js",
  paths: {
      jquery: '../assets/plugins/jquery/jquery-1.12.4.min',
      polyfiller: '../assets/plugins/webshim/polyfiller',
      ax5core: '../assets/plugins/ix/ax5ui.all',
      //ax5layout: '../assets/plugins/ix/ax5layout',
      jqueryui: '../assets/plugins/jquery/jquery-ui.min',
      fancytree: '../assets/plugins/fancytree/jquery.fancytree-all',
      moment: '../assets/plugins/fullcalendar-3.2.0/moment',
      fullcalendar: '../assets/plugins/fullcalendar-3.2.0/fullcalendar.min'
  },
  shim:{
      'polyfiller':{
          exports:'polyfiller' //로드된 angular 라이브러리는 angular 라는 이름의 객체로 사용할 수 있게 해준다
      },
      'ax5core':{
          exports:'ax5core' //로드된 angular 라이브러리는 angular 라는 이름의 객체로 사용할 수 있게 해준다
      },
      // 'ax5layout':{
      //     exports:'ax5layout' //로드된 angular 라이브러리는 angular 라는 이름의 객체로 사용할 수 있게 해준다
      // },
      'jqueryui':{
          exports:'jqueryui' //로드된 angular 라이브러리는 angular 라는 이름의 객체로 사용할 수 있게 해준다
      },
      'fancytree':{
        deps:['jqueryui'],
          exports:'fancytree' //로드된 angular 라이브러리는 angular 라는 이름의 객체로 사용할 수 있게 해준다
      },
      'fullcalendar':{
        deps:['jqueryui','moment'],
          exports:'fullcalendar' //로드된 angular 라이브러리는 angular 라는 이름의 객체로 사용할 수 있게 해준다
      }
  }
});
