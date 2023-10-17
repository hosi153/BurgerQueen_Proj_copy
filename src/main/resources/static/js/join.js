const makeNewButton = document.getElementById("newMemberBTN");


if(makeNewButton){
    makeNewButton.addEventListener('click',event =>{
        fetch('/api/member/new',{
            method :'POST',
            headers : {"Content-Type": "application/json",},
            body : JSON.stringify({
                email:document.getElementById("email").value,
                password: document.getElementById("password").value,
                memberName: document.getElementById("memberName").value,
                phone: document.getElementById("phone").value,
            })
        })
            .then((response)=> {
                if(response.ok) alert('등록 완료되었습니다.');
                else alert('다른 정보를 입력해 주세요.')
            });
    })
}