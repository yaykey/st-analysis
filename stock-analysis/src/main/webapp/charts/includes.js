window.FlashCanvasOptions = {
  swfPath: base + '/js/HumbleSoftware/lib/FlashCanvas/bin/'
};
yepnope([
  // Libs
  base + '/js/HumbleSoftware/lib/jquery/jquery-1.7.1.min.js',
  base + '/js/HumbleSoftware/lib/flotr2/lib/bean.js',
  base + '/js/HumbleSoftware/lib/flotr2/lib/underscore-min.js',
  {
  test : (navigator.appVersion.indexOf("MSIE") != -1  && parseFloat(navigator.appVersion.split("MSIE")[1]) < 9),
    // Load for IE < 9
    yep : [
      base + '/js/HumbleSoftware/lib/FlashCanvas/bin/flashcanvas.js',
      /*base + '/js/HumbleSoftware/lib/flotr2/lib/excanvas.js',*/
      base + '/js/HumbleSoftware/lib/flotr2/lib/base64.js'
    ]
  },
  base + '/js/HumbleSoftware/lib/flotr2/lib/canvas2image.js',
  /*base + '/js/HumbleSoftware/lib/flotr2/lib/canvastext.js',*/
  base + '/js/HumbleSoftware/lib/bonzo/bonzo.min.js',

  // Flotr
  base + '/js/HumbleSoftware/lib/flotr2/js/Flotr.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/DefaultOptions.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/Color.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/Date.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/DOM.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/EventAdapter.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/Graph.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/Axis.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/Series.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/Text.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/types/lines.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/types/bars.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/types/points.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/plugins/selection.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/plugins/legend.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/plugins/hit.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/plugins/crosshair.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/plugins/labels.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/plugins/legend.js',
  base + '/js/HumbleSoftware/lib/flotr2/js/plugins/titles.js',
  {
    test : ('ontouchstart' in window),
    nope : [
      base + '/js/HumbleSoftware/lib/flotr2/js/plugins/handles.js'
    ]
  },

  // Visualization
  base + '/js/HumbleSoftware/js/Envision.js',
  base + '/js/HumbleSoftware/js/Visualization.js',
  base + '/js/HumbleSoftware/js/Component.js',
  base + '/js/HumbleSoftware/js/Interaction.js',
  base + '/js/HumbleSoftware/js/Preprocessor.js',
  base + '/js/HumbleSoftware/js/templates/namespace.js',
  base + '/js/HumbleSoftware/js/templates/Finance.js',
  base + '/js/HumbleSoftware/js/templates/TimeSeries.js',
  base + '/js/HumbleSoftware/js/templates/Zoom.js',
  base + '/js/HumbleSoftware/js/actions/namespace.js',
  base + '/js/HumbleSoftware/js/actions/hit.js',
  base + '/js/HumbleSoftware/js/actions/selection.js',
  base + '/js/HumbleSoftware/js/actions/zoom.js',
  base + '/js/HumbleSoftware/js/adapters/namespace.js',
  base + '/js/HumbleSoftware/js/adapters/flotr/namespace.js',
  base + '/js/HumbleSoftware/js/adapters/flotr/defaultOptions.js',
  base + '/js/HumbleSoftware/js/adapters/flotr/Child.js',
  base + '/js/HumbleSoftware/js/adapters/flotr/lite-lines.js',
  base + '/js/HumbleSoftware/js/adapters/flotr/whiskers.js',
  base + '/js/HumbleSoftware/js/components/namespace.js',
  base + '/js/HumbleSoftware/js/components/QuadraticDrawing.js',

  { complete : example }
]);
