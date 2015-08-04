+function ($) {
  $(function(){
    
    var intro = introJs();

    intro.setOptions({
      steps: [
{
    element: '#tour_header',
    intro: '<p class="h4 text-uc"><strong>CrunchPrep Header</strong></p><p>Sample Text.</p>',
    position: 'bottom'
  },
      {
          element: '#tour1',
          intro: '<p class="h4 text-uc"><strong>1: Home</strong></p><p>Sample Text.</p>',
          position: 'right'
        },
        {
            element: '#tour2',
            intro: '<p class="h4 text-uc"><strong>2: Lessons</strong></p><p>Sample Text.</p>',
            position: 'right'
          },
          {
              element: '#tour3',
              intro: '<p class="h4 text-uc"><strong>3: Practice</strong></p><p>Sample Text.</p>',
              position: 'right'
            }
          ,
          {
              element: '#tour4',
              intro: '<p class="h4 text-uc"><strong>4: Flash Cards</strong></p><p>Sample Text.</p>',
              position: 'right'
            }
          ,
          {
              element: '#tour5',
              intro: '<p class="h4 text-uc"><strong>5: Take a Test</strong></p><p>Sample Text.</p>',
              position: 'right'
            }
          ,
          {
              element: '#tour6',
              intro: '<p class="h4 text-uc"><strong>6: Notes</strong></p><p>Sample Text.</p>',
              position: 'right'
            }
          ,
          {
              element: '#tour7',
              intro: '<p class="h4 text-uc"><strong>7: Bookmarks</strong></p><p>Sample Text.</p>',
              position: 'right'
            }
          ,
          {
              element: '#tour8',
              intro: '<p class="h4 text-uc"><strong>8: Reports</strong></p><p>Sample Text.</p>',
              position: 'right'
            }
          ,
          {
              element: '#tour9',
              intro: '<p class="h4 text-uc"><strong>9: Analysis</strong></p><p>Sample Text.</p>',
              position: 'top'
            }
          ,
          {
              element: '#tour10',
              intro: '<p class="h4 text-uc"><strong>10: Strength & Oppurtunities</strong></p><p>Sample Text.</p>',
              position: 'top'
            }
         
          ,
          {
              element: '#tour11',
              intro: '<p class="h4 text-uc"><strong>11: What should i do now</strong></p><p>Sample Text.</p>',
              position: 'top'
            }
        
          ,
          {
              element: '#tour16',
              intro: '<p class="h4 text-uc"><strong>12: Notifications</strong></p><p>Sample Text.</p>',
              position: 'left'
            }
          ,
          {
              element: '#tour17',
              intro: '<p class="h4 text-uc"><strong>13: Settings & Logout</strong></p><p>Sample Text.</p>',
              position: 'left'
            }
          
      ],
      showBullets: true,
    });

    intro.start();

  });
}(jQuery);