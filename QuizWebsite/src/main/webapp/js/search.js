const input = document.getElementById("search-input");
const searchBtn = document.getElementById("search-button");
const usersList = document.getElementById("search-users-list");
const usersContainer = document.querySelector(".search-users");

searchBtn.addEventListener("click", getUsers);
input.addEventListener("keydown", (event) => {
    if (event.key == "Enter") {
        getUsers();
    }
});

function getUsers() {
    const baseUrl = "/search/SearchServlet";
    const params = `?search=${input.value}`;
    const url = baseUrl + params;

    fetch(url)
        .then((res) => {
            if (!res.ok) {
                throw new Error("Something happened");
            }
            return res.json();
        })
        .then((obj) => {
            usersList.innerHTML = ""; // empty last searchs
            if (usersContainer.classList.contains("show")) {
                usersContainer.classList.remove("show");
            }
            obj.list.map((user) => {
                let liTag = document.createElement("li");
                liTag.innerHTML = `<p><a href="${user.link}">${user.username}</a></p>`;
                usersList.appendChild(liTag);
            });
            if (obj.list.length != 0) {
                usersContainer.classList.add("show");
            }
        })
        .catch((err) => {
            console.error(err);
        });
}