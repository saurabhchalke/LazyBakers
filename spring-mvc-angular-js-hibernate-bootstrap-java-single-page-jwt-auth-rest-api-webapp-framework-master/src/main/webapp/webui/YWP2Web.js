var ywp2WebModule = angular.module('YWP2Web',
                                    [
                                        'ngAnimate',
                                        'ngMessages',
                                        'ngRoute',
                                        'ngCookies',
                                        'App.Common',
                                        'App.Admin',
                                        'App.Auth',
                                        'App',
                                        'ngCart',
                                        'ngCart.directives',
                                        'ngCart.fulfilment',
                                        
                                    ]);

ywp2WebModule.config(['$routeProvider',
    function ($routeProvider){
        $routeProvider
            .when('/home', {
//                controller: 'HomeController',
//                templateUrl: 'webui/views/home.html',
//                controllerAs: 'home'
//            	
            	controller: 'HomeController',
            	templateUrl: 'webui/views/index.html',
            	controllerAs: 'home'
            })

            .when('/admin.login', {
                controller: 'LoginController',
                templateUrl: 'webui/views/login.html',
                controllerAs: 'lc'
            })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'webui/views/login.html',
                controllerAs: 'lc'
            })

            .when('/register', {
//                controller: 'RegisterController',
//                templateUrl: 'webui/views/register.html',
//                controllerAs: 'rc'
                	
                    controller: 'RegisterController',
                    templateUrl: 'webui/views/register1.html',
                    controllerAs: 'rc'
            	

            })
            
            .when('/menu', {
                controller: 'MenuController',
                templateUrl: 'webui/views/menus.html',
                controllerAs: 'mc'
            })
            
            .when('/orderPizza', {
                controller: 'CartController',
                templateUrl: 'webui/views/Cart.html',
                controllerAs: 'ngc'
            })
            
            

            .when('/access-denied', {
                controller: 'LoginController',
                templateUrl: 'webui/views/access-denied.html',
                controllerAs: 'lc'
            })

            .when('/admin', {
                controller: 'AdminController',
                templateUrl: 'webui/views/Admin.html',
                controllerAs: 'adm'
            })
            .when('/user', {
                controller: 'UserController',
                templateUrl: 'webui/views/User.html',
                controllerAs: 'usr'
            })

            .when('/app', {
                controller: 'AppController',
                templateUrl: 'webui/views/dashboard.html',
                controllerAs: 'app'
            })

            .otherwise({ redirectTo: '/home' });
    }
]);

ywp2WebModule.run(['$rootScope', '$location', '$cookieStore', '$http',
    function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + $rootScope.globals.token;
            $rootScope.currentUser = $rootScope.globals.currentUser;
        }

        $rootScope.isSubmitted = false;

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            console.log('received event: ' + event + ' from: ' + current + ' to go to next: ' + next);
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/user','/admin','/menu','/login', '/register', '/admin.login', '/adm.register', '/admin', '/app', '/dashboard']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            $rootScope.currentUser = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                if($location.path().indexOf('admin') > -1) {
                    $location.path('/admin.login');
                } else if($location.path().indexOf('app') > -1) {
                    $location.path('/login');
                } else {
                    $location.path('/home');
                }
            }
        });
    }
]);