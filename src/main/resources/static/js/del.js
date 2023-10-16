const delAll = document.querySelectorAll('#trDiv .del');

[].forEach.call(delAll, function(col){
    col.addEventListener("click" , click , false );
});


//카트 페이지에서 상품삭제 버튼 클릭시 단건 delete 발생
function click(e){
        // console.log(this)
        let cartId = document.getElementById('cartId').value;
        let index = this.getAttribute("id")
        let productId = this.getAttribute("prdId")
        let price = parseInt(this.getAttribute("price"))
        let discountPrice = parseInt(this.getAttribute("discountPrice"))

        console.log(cartId, productId)
        fetch(`/api/cart/${cartId}/${productId}`,{
            method :'DELETE',
            headers : {"Content-Type": "application/json",},
        })
            .then((response) => {
                console.log(response)
                if(response.ok){
                    totalQuantity -= tmpCart[index]["quantity"];
                    totalPrice -= price*(tmpCart[index]["quantity"]);
                    totalDiscountPrice -= discountPrice*(tmpCart[index]["quantity"]);

                    if(totalQuantity>0){

                        // document.querySelector("#tBody").deleteRow(index)
                        document.querySelector("#tcount").innerText=totalQuantity;
                        document.querySelector("#tprice").innerText=totalPrice;
                        document.querySelector("#tdiscountprice").innerText=totalDiscountPrice;
                        document.querySelector("#tdiscount").innerText=totalPrice-totalDiscountPrice;
                        location.href=`/cart`
                    }
                    else location.href=`/empty-cart`;
                    }
                else{
                    alert("잠시 후 다시 시도해주세요.")
                }
            })

}
