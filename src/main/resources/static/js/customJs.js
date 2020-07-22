


function check(input)
{

    let checkboxes = document.getElementsByClassName("radioCheck");
   

    for(let i = 0; i < checkboxes.length; i++)
    {
    	
        //uncheck all
        if(checkboxes[i].checked == true)
        {
            checkboxes[i].checked = false;
        }
    }

    //set checked of clicked object
    if(input.checked == true)
    {
        input.checked = false;
    }
    else
    {
        input.checked = true;
    }
}



