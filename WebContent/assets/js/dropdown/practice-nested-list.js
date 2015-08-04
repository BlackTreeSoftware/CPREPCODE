// Select the main list and add the class "hasSubmenu" in each LI that contains an UL
$('.tree-view-practice ul').each(function(){
  $this = $(this);
  $this.find("li").has("ul").addClass("hasSubmenu");
});
// Find the last li in each level
$('li:last-child').each(function(){
  $this = $(this);
  // Check if LI has children
  if ($this.children('.tree-view-practice ul').length === 0){
    // Add border-left in every UL where the last LI has not children
    $this.closest('.tree-view-practice ul').css("");
  } else {
    // Add border in child LI, except in the last one
    $this.closest('.tree-view-practice ul').children("li").not(":last").css("");
    // Add the class "addBorderBefore" to create the pseudo-element :defore in the last li
    $this.closest('.tree-view-practice ul').children("li").last().children("a").addClass("");
    // Add margin in the first level of the list
    $this.closest('.tree-view-practice ul').css("margin-top","0px");
    // Add margin in other levels of the list
    $this.closest('.tree-view-practice ul').find("li").children("ul").css("margin-top","0px");
  };
});
// Add bold in li and levels above
$('ul li').each(function(){
  $this = $(this);
  $this.mouseenter(function(){
    $( this ).children("a").css({});
  });
  $this.mouseleave(function(){
    $( this ).children("a").css({});
  });
});
// Add button to expand and condense - Using FontAwesome
$('.tree-view-practice ul li.hasSubmenu').each(function(){
  $this = $(this);
  $this.prepend("<a href='#'><i class='fa fa-chevron-down' style='color:#11a2a4'></i><i style='display:none; color:#11a2a4;' class='fa fa-chevron-right'></i></a>");
  $this.children("a").not(":last").removeClass().addClass("toogle");
});
// Actions to expand and consense
$('.tree-view-practice ul li.hasSubmenu a.toogle').click(function(){
  $this = $(this);
  $this.closest("li").children("ul").toggle("slow");
  $this.children("i").toggle();
  return false;
});