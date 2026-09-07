function init(){
    const input = document.getElementById('username');
    const info = document.getElementById('username-info');

    input.onblur = function(){
        const username = input.value.trim();

        if (username === '') {
            info.textContent = '';
            return;
        }

        const xhr = new XMLHttpRequest();
        xhr.open(
            'GET',
            'checkUsername?username=' + encodeURIComponent(username),
            true
        );

        xhr.onreadystatechange = function(){
            if (xhr.readyState !== XMLHttpRequest.DONE) {
                return;
            }

            if (xhr.status === 200) {
                info.textContent = 'valid username';
                info.style.color = 'green';
            } else if (xhr.status === 401) {
                info.textContent = 'invalid username';
                info.style.color = 'red';
            } else {
                info.textContent = 'failed to check username';
                info.style.color = 'red';
            }
        };

        xhr.send();
    };
}

init();
