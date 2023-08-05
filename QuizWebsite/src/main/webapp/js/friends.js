const acceptButtons = document.querySelectorAll('.acc-btn');
const rejectButtons = document.querySelectorAll('.rej-btn');

function handleFriend(event, toAdd) {
    const parentLi = event.target.closest('li');
    const from = parentLi.id.split('-')[1];
    const to = document.getElementById("username").innerText.trim();
    const baseUrl = "/friends/FriendsServlet";
    const params = `?toAdd=${toAdd}`;
    const url = baseUrl + params;
    const data = {
        from: from,
        to: to,
    };

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data), // Convert data to JSON string
    };

    fetch(url, options)
        .then((res) => {
            if (!res.ok) {
                throw new Error("Something happened");
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    parentLi.remove();
}

acceptButtons.forEach(button => {
    button.addEventListener('click', (e) => handleFriend(e, true));
});

rejectButtons.forEach(button => {
    button.addEventListener('click', (e) => handleFriend(e, false));
});