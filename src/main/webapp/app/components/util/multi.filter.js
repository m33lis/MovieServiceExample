/**
 * Created by m3l on 3/3/17.
 */
(function () {
    'use strict';

    angular
        .module('movieServiceExampleApp')
        .filter('multi', multi);

    function multi() {
        return multiFilter;

        function multiFilter(items, searchKeys) {
            var retArray = [];

            if (searchKeys.length>0) {
                _.each(searchKeys, function (key) {
                    _.each(items, function (item) {
                        if (!_.isUndefined(item.category) && item.category.id === key.id) {
                            retArray.push(item);
                        }
                    });
                });
                return retArray;
            } else {
                return items;
            }
        }
    }
})();
