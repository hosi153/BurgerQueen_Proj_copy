const delBtn = document.getElementById("delBtn");

//카트 페이지에서 상품삭제 버튼 클릭시 단건 delete 발생
if(delBtn){
    delBtn.addEventListener('click',event =>{
        let index = this.getAttribute("index")
        //let productId = this.getAttribute()'' +

        console.log(`cartId: ${cartId}`)
        fetch(`/api/cart/${cartId}`,{
            method :'DELETE',
            headers : {"Content-Type": "application/json",},
        })
            .then((response) => {
                if(response.ok){return response.json()}
                throw new Error(`Status: ${response.status} ! 요청 처리에 실패하였습니다 !`);
            }).then(data => {
            location.href=`/cart`;
        })
            .catch(error => {
                alert('삭제실패! 잠시 후 시도해주세요.');
            })
    })
}
