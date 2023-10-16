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

        let selName = `#num${index}`
        document.querySelector(selName).innerText=tmpCart[index]["quantity"];
        // location.href=`/cart`
        document.querySelector("#tcount").innerText=numberWithCommas(totalQuantity);
        document.querySelector("#tprice").innerText=numberWithCommas(totalPrice);
        document.querySelector("#tdiscountprice").innerText=numberWithCommas(totalDiscountPrice);
        document.querySelector("#tdiscount").innerText=numberWithCommas(totalPrice-totalDiscountPrice);
    }else{
        // alert("주문가능수량 초과")
        Swal.fire('',`주문가능수량을 초과하였습니다(최대 : ${max} 개)`,'warning')
    }

    console.log(tmpCart)

}
