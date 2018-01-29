'use strict';

angular.module('ngCart', ['ngCart.directives'])

    .config([function () {

    }])

    .provider('$ngCart', function () {
        this.$get = function () {
        };
    })

    .run(['$rootScope', 'ngCart','ngCartItem', 'store', function ($rootScope, ngCart, ngCartItem, store) {


        $rootScope.$on('ngCart:change', function(){
            ngCart.$save();
        });

        if (angular.isObject(store.get('cart'))) {
            ngCart.$restore(store.get('cart'));

        } else {
            ngCart.init();
        }

    }])

    .service('ngCart', ['$rootScope', 'ngCartItem', 'store', function ($rootScope, ngCartItem, store) {


    	this.items = [];

        this.init = function(){
            this.$cart = {
                shipping : null,
                taxRate : null,
                tax : null,
                items : []
            };
        };

        this.addItem = function (id, name, price, quantity, data) {

            var inCart = this.getItemById(id);

            if (typeof inCart === 'object'){
                //Update quantity of an item if it's already in the cart
                console.log("Item alreay in cart!")
                inCart.setQuantity(quantity, false);
            } else {
                var newItem = new ngCartItem(id, name, price, quantity, data);
                this.$cart.items.push(newItem);
                console.log("Adding Items to cart!");
                console.log(this.$cart.items);
                $rootScope.$broadcast('ngCart:itemAdded', newItem);
            }

            $rootScope.$broadcast('ngCart:change', {});
        };

        this.getItemById = function (itemId) {
            var items = this.getCart().items;
            var build = false;

            angular.forEach(items, function (item) {
                if  (item.getId() === itemId){
                    build = item;
                }
            });
            return build;
        };

        this.setShipping = function(shipping){
            this.$cart.shipping = shipping;
            return this.getShipping();
        };

        this.getShipping = function(){
            if (this.getCart().items.length == 0) return 0;
            return  this.getCart().shipping;
        };

        this.setTaxRate = function(taxRate){
            this.$cart.taxRate = +parseFloat(taxRate).toFixed(2);
            return this.getTaxRate();
        };

        this.getTaxRate = function(){
            return this.$cart.taxRate
        };

        this.getTax = function(){
            return +parseFloat(((this.getSubTotal()/100) * this.getCart().taxRate )).toFixed(2);
        };

        this.setCart = function (cart) {
            this.$cart = cart;
            return this.getCart();
        };

        this.getCart = function(){
            return this.$cart;
        };

        this.getItems = function(){
            return this.getCart().items;
        };

        this.getTotalItems = function () {
            var count = 0;
            var items = this.getItems();
            angular.forEach(items, function (item) {
                count += item.getQuantity();
            });
            return count;
        };

        this.getTotalUniqueItems = function () {
            return this.getCart().items.length;
        };

        this.getSubTotal = function(){
            var total = 0;
            angular.forEach(this.getCart().items, function (item) {
                total += item.getTotal();
            });
            return +parseFloat(total).toFixed(2);
        };

        this.totalCost = function () {
            return +parseFloat(this.getSubTotal() + this.getShipping() + this.getTax()).toFixed(2);
        };

        this.removeItem = function (index) {
            this.$cart.items.splice(index, 1);
            $rootScope.$broadcast('ngCart:itemRemoved', {});
            $rootScope.$broadcast('ngCart:change', {});
            //console.log("removing " +index +"th item");
        };

        this.removeItemById = function (id) {
            var cart = this.getCart();
            angular.forEach(cart.items, function (item, index) {
                if  (item.getId() === id) {
                    cart.items.splice(index, 1);
                    console.log("removing item with " + id);
                }
            });
            this.setCart(cart);
            $rootScope.$broadcast('ngCart:itemRemoved', {});
            $rootScope.$broadcast('ngCart:change', {});
        };

        this.empty = function () {
            
            $rootScope.$broadcast('ngCart:change', {});
            this.$cart.items = [];
            localStorage.removeItem('cart');
        };

        this.toObject = function() {

            if (this.getItems().length === 0) return false;

            var items = [];
            angular.forEach(this.getItems(), function(item){
                items.push (item.toObject());
            });

            return {
                shipping: this.getShipping(),
                tax: this.getTax(),
                taxRate: this.getTaxRate(),
                subTotal: this.getSubTotal(),
                totalCost: this.totalCost(),
                items:items
            }
        };


        this.$restore = function(storedCart){
            var _self = this;
            _self.init();
            _self.$cart.shipping = storedCart.shipping;
            _self.$cart.tax = storedCart.tax;

            angular.forEach(storedCart.items, function (item) {
                _self.$cart.items.push(new ngCartItem(item._id,  item._name, item._price, item._quantity, item._data));
            });
            this.$save();
        };

        this.$save = function () {
            return store.set('cart', JSON.stringify(this.getCart()));
        }

    }])

    .factory('ngCartItem', ['$rootScope', '$log', function ($rootScope, $log) {
    	//factory is like a class....has all constructor, setter and getter
        var item = function (id, name, price, quantity, data) {
        	console.log("In Constructor");

            this.setId(id);
            this.setName(name);
            this.setPrice(price);
            this.setQuantity(quantity);
            this.setData(data);
        };


        item.prototype.setId = function(id){
            if (id)  this._id = id;
            else {
                $log.error('An ID must be provided');
            }
        };

        item.prototype.getId = function(){
            return this._id;
        };


        item.prototype.setName = function(name){
            if (name)  this._name = name;
            else {
                $log.error('A name must be provided');
            }
        };
        item.prototype.getName = function(){
            return this._name;
        };

        item.prototype.setPrice = function(price){
            var priceFloat = parseFloat(price);
            if (priceFloat) {
                if (priceFloat <= 0) {
                    $log.error('A price must be over 0');
                } else {
                    this._price = (priceFloat);
                }
            } else {
                $log.error('A price must be provided');
            }
        };
        item.prototype.getPrice = function(){
            return this._price;
        };


        item.prototype.setQuantity = function(quantity, relative){


            var quantityInt = parseInt(quantity);
            if (quantityInt % 1 === 0){
                if (relative === true){
                    this._quantity  += quantityInt;
                } else {
                    this._quantity = quantityInt;
                }
                if (this._quantity < 1) this._quantity = 1;

            } else {
                this._quantity = 1;
                $log.info('Quantity must be an integer and was defaulted to 1');
            }
            $rootScope.$broadcast('ngCart:change', {});

        };

        item.prototype.getQuantity = function(){
            return this._quantity;
        };

        item.prototype.setData = function(data){
            if (data) this._data = data;
        };

        item.prototype.getData = function(){
            if (this._data) return this._data;
            else $log.info('This item has no data');
        };


        item.prototype.getTotal = function(){
            return +parseFloat(this.getQuantity() * this.getPrice()).toFixed(2);
        };


        item.prototype.toObject = function() {
            return {
                id: parseInt(this.getId()),
                name: this.getName(),
                price: this.getPrice(),
                quantity: this.getQuantity(),
                data: this.getData(),
                total: this.getTotal()
            }
        };

        return item;

    }])

    .service('store', ['$window', function ($window) {

        return {

            get: function (key) {
                if ($window.localStorage [key]) {
                    var cart = angular.fromJson($window.localStorage [key]);
                    return JSON.parse(cart);
                }
                return false;

            },


            set: function (key, val) {

                if (val === undefined) {
                    $window.localStorage .removeItem(key);
                } else {
                    $window.localStorage [key] = angular.toJson(val);
                }
                return $window.localStorage [key];
            }
        }
    }])

    .controller('CartController',['$scope','$compile', 'ngCart','filterFilter', function($scope, $compile, ngCart,filterFilter) {
        $scope.ngCart = ngCart;
        $scope.random = 100;
        console.log("In Cartcontroller");
        (function initController() {

            console.log("In self calling");
            
            

            var toppings = [{"toppingId":0,"toppingName":"Tomato","price":50.0,"stock":100,"vegetarian":true},
                            {"toppingId":2,"toppingName":"Capsicum","price":50.0,"stock":100,"vegetarian":true},
                            {"toppingId":3,"toppingName":"Onion","price":50.0,"stock":100,"vegetarian":true},
                            {"toppingId":4,"toppingName":"Mushroom","price":50.0,"stock":100,"vegetarian":true},
                            {"toppingId":5,"toppingName":"Corn","price":50.0,"stock":100,"vegetarian":true},
                            {"toppingId":6,"toppingName":"Cheese","price":50.0,"stock":100,"vegetarian":true},
                            {"toppingId":7,"toppingName":"Chicken Tikka","price":60.0,"stock":100,"vegetarian":false},
                            {"toppingId":8,"toppingName":"Chicken Sausage","price":60.0,"stock":100,"vegetarian":false},
                            {"toppingId":9,"toppingName":"Chicken Peri Peri","price":60.0,"stock":100,"vegetarian":false}]


            $.map( toppings, function( json_object ) {
                  json_object["selected"] = false;
            });

                
            /*var topping;
            $http.get(BackendCfg.url + '/api/topping').then(function(response) {
            	
            	topping = response.data;
            	$.map( topping, function( json_object ) {
                    json_object["selected"] = false;
            	});
            	$scope.toppings = topping;
            	$scope.toppings2 = topping;
            });*/

            
            
            
            


            $scope.toppings = toppings;

            function getSelectedToppings() {
                // console.log("called getSelectedToppings");
                return filterFilter($scope.toppings, { selected: true });
            };



            // console.log(toppings);

            var base = [{"baseId":1,"baseName":"Regular","price":20.0},
                        {"baseId":2,"baseName":"Fresh Pan","price":30.0},
                        {"baseId":3,"baseName":"Thin Crust","price":30.0},
                        {"baseId":4,"baseName":"Cheese Burst","price":40.0},
                        {"baseId":5,"baseName":"Double Cheese Burst","price":40.0},
                        {"baseId":6,"baseName":"LB Special","price":40.0}]

            $.map( base, function( json_object ) {
                if(json_object["baseName"] == "Regular")
                    json_object["selected"] = true;
                else  
                  json_object["selected"] = false;
            });


            $scope.base = base;

            $scope.setChoiceForBase = function (baseID) {
                 
                    angular.forEach($scope.base, function(base){
                            if(base.baseId == baseID)
                                base.selected = true;
                            else
                                base.selected = false;
                       
                     });
            };


            $scope.PizzaSize = [{"Name":"Regular","price":80,"selected":false},
                            {"Name":"Medium","price":100,"selected":true},
                            {"Name":"Large","price":120,"selected":false}
                        ];

             
            $scope.setChoiceForSize = function (name) {
                     
                    angular.forEach($scope.PizzaSize, function(obj){
                            if(obj.Name == name)
                                obj.selected = true;
                            else
                                obj.selected = false;
                                               
                     });
            
            };

            
            $scope.addCustomizedPizzaToCart = function(){
                

                console.log("called addCustomizedPizzaToCart");
                var topping = getSelectedToppings();
                
                var amt = 0;
                var top= [];
                $.map( topping, function( json_object ) {
                  
                    amt += json_object.price;
                    top.push(json_object.toppingId);
                  
                });
                console.log(top);
                var baseId = null;
                angular.forEach($scope.base, function(_base){
                        if(_base.selected == true){
                            amt+= _base.price;
                            baseId = _base.baseId;
                        }
                     });
                     console.log(baseId);
                var size = "Medium";
                angular.forEach($scope.PizzaSize, function(Piz_size){
                        if(Piz_size.selected == true){
                            amt += Piz_size.price;
                            size = Piz_size.Name;
                        }
                     });
                console.log(size);
                
                var data = {
                    "toppings" : top,
                    "baseId" : baseId,
                    "size" : size  
                };




                //addItem(id, name, price, q, data);
                ngCart.addItem($scope.random++, "Customized_pizza", amt, 1, data);
                
                
            };

/*
            var pizza  =  {
            "pizzaId" : 1,
            "pizzaName" : "chicken tikka",
            "pizzaDesc" : "pizza desc",
            "price" : 21.44,
            "size" : 1,
            "customized" : 1,
            "base" : {
                "baseId" : 1,
                "baseName" : "thin",
                "price" : 21.4
            }
        };


        var res = $compile($(`<div class="col-xs-6 col-sm-3">
        <h4>`+ pizza.pizzaName + `</h4>
        <p> `+   pizza.pizzaDesc +` <br> Base: `+ pizza.base.baseName +` </P>
        <p> $`+ pizza.price +`</p>
        <ngcart-addtocart id="`+ pizza.pizzaId +`" name="`+ pizza.pizzaName + `" price="`+ pizza.price +`" quantity="1" quantity-max="5">Add to Cart</ngcart-addtocart>
    </div>`))($scope);


        res.appendTo('#standard_pizzas');*/
        
        })();




        



    }])

    .value('version', '0.0.3-rc.1');
;'use strict';


angular.module('ngCart.directives', ['ngCart.fulfilment'])

    .controller('CartController1',['$scope', 'ngCart', function($scope, ngCart) {
        $scope.ngCart = ngCart;

        console.log("In controller1");

               
    }])
    .directive('ngcartAddtocart', ['ngCart', function(ngCart){
        return {
            restrict : 'E',
            controller : 'CartController1',//if you rename to CartController then the origninal will be called 5 times, so better keep this
            scope: {
                id:'@',
                name:'@',
                quantity:'@',
                quantityMax:'@',
                price:'@',
               
                data:'='
            },
            transclude: true,
            templateUrl: 'template/ngCart/addtocart.html',
            link:function(scope, element, attrs){
                scope.attrs = attrs;
                scope.inCart = function(){
                    return ngCart.getItemById(attrs.id);
                };

                if (scope.inCart()){
                    scope.q = ngCart.getItemById(attrs.id).getQuantity();
                } else {
                    scope.q = parseInt(scope.quantity);
                }

                scope.qtyOpt = [];
                for (var i = 1; i <= scope.quantityMax; i++) {
                    scope.qtyOpt.push(i);
                }

            }

        };
    }])

    .directive('ngcartCart', [function(){
        return {
            restrict : 'E',
            controller : 'CartController1',
            scope: {},
            templateUrl: 'template/ngCart/cart.html',
            link:function(scope, element, attrs){

            }
        };
    }])

    .directive('ngcartSummary', [function(){
        return {
            restrict : 'E',
            controller : 'CartController1',
            scope: {},
            transclude: true,
            templateUrl: 'template/ngCart/summary.html'
        };
    }])

    .directive('ngcartCheckout', [function(){
        return {
            restrict : 'E',
            controller : ('CartController1', ['$scope', 'ngCart', 'fulfilmentProvider', function($scope, ngCart, fulfilmentProvider) {
                $scope.ngCart = ngCart;

                $scope.checkout = function () {
                    fulfilmentProvider.setService($scope.service);
                    fulfilmentProvider.setSettings($scope.settings);
                    var promise = fulfilmentProvider.checkout();
                    console.log(promise);
                }
            }]),
            scope: {
                service:'@',
                settings:'='
            },
            transclude: true,
            templateUrl: 'template/ngCart/checkout.html'
        };
    }]);;
angular.module('ngCart.fulfilment', [])
    .service('fulfilmentProvider', ['$injector', function($injector){

        this._obj = {
            service : undefined,
            settings : undefined
        };

        this.setService = function(service){
            this._obj.service = service;
        };

        this.setSettings = function(settings){
            this._obj.settings = settings;
        };

        this.checkout = function(){
            var provider = $injector.get('ngCart.fulfilment.' + this._obj.service);
              return provider.checkout(this._obj.settings);

        }

    }])


.service('ngCart.fulfilment.log', ['$q', '$log', 'ngCart', function($q, $log, ngCart){

        this.checkout = function(){

            var deferred = $q.defer();

            $log.info(ngCart.toObject());
            deferred.resolve({
                cart:ngCart.toObject()
            });

            return deferred.promise;

        }

 }])

.service('ngCart.fulfilment.http', ['$http', 'ngCart', function($http, ngCart){

		console.log(ngCart.toObject());

        // document.write(JSON.stringify(ngCart.toObject()));

        
        this.checkout = function(){	
            return $http.post("http://localhost:8080/spring-angularjs-java-webapp-template-project/bill",
            		{data:JSON.stringify(ngCart.toObject())})
        }
 }]);