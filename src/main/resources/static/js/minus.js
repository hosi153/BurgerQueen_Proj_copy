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

        let selName = `#num${index}`
        document.querySelector(selName).innerText=tmpCart[index]["quantity"];
        // location.href=`/cart`
        document.querySelector("#tcount").innerText=numberWithCommas(totalQuantity);
        document.querySelector("#tprice").innerText=numberWithCommas(totalPrice);
        document.querySelector("#tdiscountprice").innerText=numberWithCommas(totalDiscountPrice);
        document.querySelector("#tdiscount").innerText=numberWithCommas(totalPrice-totalDiscountPrice);
    }else{
        // alert("최소 1개 주문")
        Swal.fire('',`최소 1개 이상 주문해주세요.`,'warning')
    }

    console.log(tmpCart)


}
