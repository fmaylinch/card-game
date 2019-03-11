console.log("Games js loaded");

axios.get("/api/games")
    .then(function(response) {

        const games = response.data; // In axios you get a response object with the data inside

        // This function will be called when the data comes
        // At this point, games contains the data that the end-point sends (the list of games)

        let gamesContainer = document.getElementById("games-container");

        for (let game of games) {

            const p = document.createElement("div");
            p.textContent = `Game ${game.id} is ${game.state}`;
            gamesContainer.appendChild(p);
        }
    });


// Observer pattern

var addGameBtn = document.querySelector("#add-game");

addGameBtn.addEventListener("click", function() {

    console.log("Adding game");

    axios.post("/api/games")
        .then(function(response) {
            const gameId = response.data;
            console.log("Game added: " + gameId);

            //location.href = "/games.html";
            location.reload();
        })
        .catch(function(error) {
            console.log("ERROR", error);
        });
});


