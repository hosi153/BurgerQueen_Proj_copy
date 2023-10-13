const minusall = document.querySelectorAll('#trDiv .minus');

[].forEach.call(minusall, function(col){
    col.addEventListener("click" , click , false );
});


function click(e){
    console.log("minus")
    let index = this.getAttribute("id")
    let q = tmpCart[index]["quantity"];
    let price = parseInt(this.getAttribute("price"))
    let discountPrice = parseInt(this.getAttribute("discountPrice"))

    if(q>1){
        tmpCart[index]["quantity"] --;
        totalQuantity --;
        totalPrice -= price;
        totalDiscountPrice -= discountPrice;
    }else{
        alert("최소 1개 주문")}

    console.log(tmpCart)
    let selName = `#num${index}`
    document.querySelector(selName).innerText=tmpCart[index]["quantity"];
    document.querySelector("#tcount").innerText=totalQuantity;
    document.querySelector("#tprice").innerText=totalPrice;
    document.querySelector("#tdiscountprice").innerText=totalDiscountPrice;
}
