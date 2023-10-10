const cartBtn = document.getElementById("cartBTN");

if(cartBtn){
    cartBtn.addEventListener('click',event =>{
        let loginId = document.getElementById('loginId').value;
        console.log(`login req id : ${loginId}`)
        fetch(`/api/member/${loginId}`,{
            method :'GET',
            headers : {"Content-Type": "application/json",}
        })
            .then((response) => {
                if(response.ok){return response.json()}
                throw new Error(`Status: ${response.status} ! 요청 처리에 실패하였습니다 !`);
            }).then(data => {
            sessionStorage.setItem('id', data.loginId);
            sessionStorage.setItem('name', data.name);
            location.href=`/articles`;
        })
            .catch(error => {
                alert('ID를 다시 입력해 주세요.');
            })
    })
}