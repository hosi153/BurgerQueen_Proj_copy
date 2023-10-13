const plusall = document.querySelectorAll('#trDiv .plus');



[].forEach.call(plusall, function(col){
    col.addEventListener("click" , click , false );
});


function click(e){
    console.log("plus")
    let index = this.getAttribute("id")
    let q = tmpCart[index]["quantity"];
    let max = parseInt(this.getAttribute("max"))
    let price = parseInt(this.getAttribute("price"))
    let discountPrice = parseInt(this.getAttribute("discountPrice"))
    if(q<max){
        tmpCart[index]["quantity"] ++;
        totalQuantity ++;
        totalPrice += price;
        totalDiscountPrice += discountPrice;
    }else{
        alert("주문가능수량 초과")}

    console.log(tmpCart)
    let selName = `#num${index}`
    document.querySelector(selName).innerText=tmpCart[index]["quantity"];
    document.querySelector("#tcount").innerText=totalQuantity;
    document.querySelector("#tprice").innerText=totalPrice;
    document.querySelector("#tdiscountprice").innerText=totalDiscountPrice;
}
