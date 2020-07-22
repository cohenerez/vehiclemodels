$( document ).ready(function() {
  
  // SUBMIT FORM
    $("#customersForm").submit(function(event) {
    // Prevent the form  submitting via the browser.
    event.preventDefault();
        let responseData = {customerId: "Fiat", customerName: "500", status: "white"};

        responseData = getCustomerData(responseData);

    ajaxPost(responseData);
  });

function ajaxPost(responseData){
    let id = responseData.customerId.valueOf();
    let url = "./delete/" + id;
    printData(responseData);
    $.ajax({
        type : "DELETE",
        url: url,

        success: function (result) {
            console.log("SUCCESS: ", result);
            displayResults(result);
            delBoxes();
        },
        error: function (e) {
            console.log("ERROR: ", e);
            displayResults(e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
 }




//---------------------------------------------------------------------

    function printData(responseData){
            

            console.log(responseData.customerId);
            console.log(responseData.customerName);
            console.log(responseData.status);
      }


//-------------------------------------------------------------------------
    function getCustomerData(responseData) {
        //Reference the Table.
        let grid = document.getElementById("customersTable");

        //Reference the CheckBoxes in Table.
        let checkBoxes = grid.getElementsByTagName("INPUT");
      
              //Loop through the CheckBoxes.
        for (let i = 0; i < checkBoxes.length; i++) {
           
            if (checkBoxes[i].checked) {
                let row = checkBoxes[i].parentNode.parentNode;
                responseData.customerId   = checkBoxes[i].value;
                responseData.customerName = row.cells[1].getElementsByTagName('a')[0].innerHTML;
                responseData.status = "Delete";
                break;
            }
         }
        return responseData;
       }
  
//---------------------------------------------------------------------------------


function displayResults(data) {
    let json = "<h4>Error</h4><pre>"
            + JSON.stringify(data, null, 4) + "</pre>";
    $('#customerMessage').html(json);
}


    function delBoxes() {

        let boxes = document.getElementsByClassName('radioCheck');
        let tableTag;
        let rowTag;
        for (let i = 0; i < boxes.length; i++) {
            let box = boxes[i];
            if (box.checked) {
                rowTag = box.parentNode.parentNode;
                tableTag = box.parentNode.parentNode.parentNode;
                tableTag.removeChild(rowTag);
            }
        }
    }

 })