(function( $ ) {
	T5.extendInitializers(function(){
		
		function init(specs) {
			 $.jqplot(specs.id,specs.data,specs.options);
		}
		
		return {
			jqPlotChart : init
		}
	});

}) ( jQuery );

