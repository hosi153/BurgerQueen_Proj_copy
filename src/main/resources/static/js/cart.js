//cart화면에서 저장하기 버튼 처리 23.10.12지영
const buyBtn = document.getElementById("cartBtn2");


//구매하기 버튼 클릭 시, cart post 및 페이지 이동
if(buyBtn){
    buyBtn.addEventListener('click',event =>{
        let cartId = document.getElementById('cartId').value;
        console.log(`cartId: ${cartId}`)
        fetch(`/api/cart/${cartId}`,{
            method :'PATCH',
            headers : {"Content-Type": "application/json",},
            body : JSON.stringify({
                cartProductPatchDtos : tmpCart
                //content: document.getElementById("editContent").value,
                // member:document.getElementById("author").value

            })
        })
            .then((response) => {
                if(response.ok){return response.json()}
                throw new Error(`Status: ${response.status} ! 요청 처리에 실패하였습니다 !`);
            }).then(data => {
            sessionStorage.setItem('cart', tmpCart);
            location.href=`/cart`;
        })
            .catch(error => {
                alert('잠시 후 시도해주세요.');
            })
    })
}
