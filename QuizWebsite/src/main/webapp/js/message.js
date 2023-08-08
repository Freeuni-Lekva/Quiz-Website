const chatBtn = document.getElementById("chat-btn");
const removeBtn = document.querySelector(".remove-btn");
const sendBtn = document.querySelector(".send-btn");
const formChat = document.querySelector(".chat-form");
const messageArea = document.getElementById("message");
const recipientInput = document.getElementById("recipient");

chatBtn.addEventListener('click', () => {
    formChat.classList.add("show");
});

removeBtn.addEventListener('click', (e) => {
    if (formChat.classList.contains("show")) {
        e.preventDefault();
        messageArea.value = "";
        recipientInput.value = "";
        formChat.classList.remove("show");
    }
});

sendBtn.addEventListener('click', (e) => {
    e.preventDefault();
   const message = messageArea.value;
   const recipient = recipientInput.value;
   messageArea.value = "";
   recipientInput.value = "";
   sendMessage(document.getElementById("username").innerText.trim(),
       recipient,
       message);
});

function sendMessage(from, to, message) {
    const url = "/message/MessageServlet";
    const data = {
        from: from,
        to: to,
        message: message
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
}