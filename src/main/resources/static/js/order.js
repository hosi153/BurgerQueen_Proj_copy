const orderBtn = document.getElementById("orderBtn");

//카트 페이지에서 상품삭제 버튼 클릭시 단건 delete 발생
if(orderBtn){
    orderBtn.addEventListener('click',event =>{
        let cartId = document.getElementById('cartId').value;
        console.log(`cartId: ${cartId}`)
        fetch(`/api/cart/${cartId}`,{
            method :'PATCH',
            headers : {"Content-Type": "application/json",},
            body : JSON.stringify({
                cartProductPatchDtos : cartListAll
                //content: document.getElementById("editContent").value,
                // member:document.getElementById("author").value

            })
        })
            .then((response) => {
                if(response.ok){return response.json()}
                throw new Error(`Status: ${response.status} ! 요청 처리에 실패하였습니다 !`);
            }).then(data => {
            sessionStorage.setItem('cart', cartListAll);
            location.href=`/cart`;
        })
            .catch(error => {
                alert('삭제실패! 잠시 후 시도해주세요.');
            })
    })
}
