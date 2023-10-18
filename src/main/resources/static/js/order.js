const orderBtn = document.getElementById("orderBtn");

//카트 페이지에서 상품삭제 버튼 클릭시 단건 delete 발생
if(orderBtn){
    orderBtn.addEventListener('click',event =>{
        let cartId = document.getElementById('cartId').value;
        console.log(`cartId: ${cartId}`)
        fetch('/api/order',{
            method :'POST',
            headers : {"Content-Type": "application/json",},
            body : JSON.stringify({
                cartId: document.getElementById('cartId').value, //키:밸류
                cartProductPatchDtos : tmpCart

                //content: document.getElementById("editContent").value,
                // member:document.getElementById("author").value

            })
        })
            .then((response) => {
                if(response.ok){
                    return response.json()
                }
                throw new Error(`Status: ${response.status} ! 요청 처리에 실패하였습니다 !`);
            })
            .then(data => {
                // certain('success',`주문을 성공했습니다.`);
                location.href=`/myPage`;

        })
            .catch(error => {
                // alert('잠시 후 시도해주세요.');
                Swal.fire('',`주문 불가능한 상품이 있습니다. 장바구니를 확인해주세요.`,'warning')
            })
    })
}


