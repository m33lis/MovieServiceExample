(function() {
    'use strict';

    angular
        .module('movieServiceExampleApp')
        .controller('MovieDetailController', MovieDetailController);

    MovieDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Movie', 'Category'];

    function MovieDetailController($scope, $rootScope, $stateParams, previousState, entity, Movie, Category) {
        var vm = this;

        vm.movie = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('movieServiceExampleApp:movieUpdate', function(event, result) {
            vm.movie = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
