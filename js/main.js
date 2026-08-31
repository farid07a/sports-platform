console.log("Sport DZ java Script is working");
/*
const clubs=[
    {
        id:1,
        name:"Biskra CITY",
        city:"Biskra",
        sport:"Football",
        image: "images/cl1.jpg"

    },
    {
        id:2,
        name:"Batna USB",
        city:"Batna",
        sport:"Tennis",
        image: "images/cl2.jpg"
    },
    {
        id:3,
        name:"JSD",
        city:"Djemorah",
        sport:"KickBoxing",
        image: "images/cl3.jpg"
    }
]

const players = [
    {
        id: 1,
        firstName: "Ahmed",
        lastName: "Ben Ali",
        clubId: 1,
        position: "Forward"
    },
    {
        id: 2,
        firstName: "Karim",
        lastName: "Messaoud",
        clubId: 1,
        position: "Midfielder"
    },
    {
        id: 3,
        firstName: "Yacine",
        lastName: "Brahimi",
        clubId: 2,
        position: "Defender"
    }
];

const matches = [
    {
        id: 1,
        clubId: 1,
        opponent: "Biskra United",
        date: "2026-09-05",
        location: "Biskra Stadium",
        result: "Upcoming"
    },
    {
        id: 2,
        clubId: 1,
        opponent: "El Oued FC",
        date: "2026-09-10",
        location: "El Oued Stadium",
        result: "Upcoming"
    },
    {
        id: 3,
        clubId: 2,
        opponent: "Biskra FC",
        date: "2026-09-08",
        location: "Biskra Stadium",
        result: "Upcoming"
    }
];
*/

    //console.log("clubGrid is null"); // Runs if it was not found
    //console.log("passe1");




const clubGrid = document.querySelector(".clubs-grid");
const clubName = document.getElementById("club-name");


function renderClubs(clubsToRender) {

    clubGrid.innerHTML = "";

    const noClubsMessage =
        document.getElementById("no-clubs-message");

    if (clubsToRender.length === 0) {
        noClubsMessage.style.display = "block";
    } 
    else {
        noClubsMessage.style.display = "none";
    }


    clubsToRender.forEach(item => {
        const card = document.createElement("article");
        card.classList.add("club-card");
        card.innerHTML = `
        <img
            src="${item.image}"
            alt="${item.name}"
            class="club-image"
        >

            <div class="club-card-content">

                <h2>${item.name}</h2>

                <p>City: ${item.city}</p>

                <p>Sport: ${item.sport}</p>

                <a href="club-details.html?id=${item.id}">
                    View Club
                </a>

            </div>
        `;
        clubGrid.appendChild(card);
    });
}


function renderClubDetails(){
    
    const params = new URLSearchParams(window.location.search);

    const clubId = Number(params.get("id"));

    const selectedClub = clubs.find(item => item.id === clubId);
/*
    if(!selectedClub){
        console.log("Club not found");
        return;
    }
  */    const detailsSection = document.querySelector(".club-details");
        const notFound = document.getElementById("club-not-found");

        if (!selectedClub) {
            detailsSection.style.display="none";
            notFound.style.display = "block";
            return;
        }
    detailsSection.style.display="block";
    notFound.style.display = "none";
        

    //  console.log(selectedClub);

    const clubCity = document.getElementById("club-city");
    const clubSport = document.getElementById("club-sport");
    const clubImage = document.getElementById("club-image");

    clubName.textContent = selectedClub.name;
    clubCity.textContent = selectedClub.city;
    clubSport.textContent = selectedClub.sport;
    
    clubImage.src = selectedClub.image;
    clubImage.alt = selectedClub.name;
    renderPlayers(clubId);
    renderMatches(clubId);

}

function renderPlayers(clubId){
 const playersList = document.getElementById("players-list");

    playersList.innerHTML = "";

    const clubPlayers = players.filter(
        player => player.clubId === clubId
    );

    if (clubPlayers.length === 0) {

        playersList.innerHTML = `
            <p class="empty-message">
                No players found.
            </p>
        `;

        return;
    }

    clubPlayers.forEach(player => {

        const playerCard = document.createElement("div");

        playerCard.classList.add("player-card");

        playerCard.innerHTML = `
            <h3>
                ${player.firstName} ${player.lastName}
            </h3>

            <p>Position: ${player.position}</p>
        `;

        playersList.appendChild(playerCard);
    });
}
 
    /*
    const playersList = document.getElementById("players-list");

    const clubPlayers = players.filter(
        player => player.clubId === clubId
    );

    clubPlayers.forEach(player => {

        const playerCard = document.createElement("div");

        playerCard.classList.add("player-card");

        playerCard.innerHTML = `
            <h3>
                ${player.firstName} ${player.lastName}
            </h3>

            <p>Position: ${player.position}</p>
        `;

        playersList.appendChild(playerCard);
    });*/


function renderMatches(clubId) {

     const matchesList = document.getElementById("matches-list");

    matchesList.innerHTML = "";

    const clubMatches = matches.filter(
        match => match.clubId === clubId
    );

    if (clubMatches.length === 0) {

        matchesList.innerHTML = `
            <p class="empty-message">
                No matches found.
            </p>
        `;

        return;
    }

    clubMatches.forEach(match => {

        const matchCard = document.createElement("div");

        matchCard.classList.add("match-card");

        matchCard.innerHTML = `
            <h3>vs ${match.opponent}</h3>

            <p>Date: ${match.date}</p>

            <p>Location: ${match.location}</p>

            <p>Status: ${match.result}</p>
        `;

        matchesList.appendChild(matchCard);
    });
    /*
    const matchesList = document.getElementById("matches-list");
    matchesList.innerHTML = "";
    const clubMatches = matches.filter(
        match => match.clubId === clubId
    );

    clubMatches.forEach(match => {

        const matchCard = document.createElement("div");

        matchCard.classList.add("match-card");

        matchCard.innerHTML = `
            <h3>vs ${match.opponent}</h3>

            <p>Date: ${match.date}</p>

            <p>Location: ${match.location}</p>

            <p>Status: ${match.result}</p>
        `;

        matchesList.appendChild(matchCard);
    });*/

    }



if(clubGrid){

    renderClubs(clubs);
}
if (clubName){
    renderClubDetails();
}


const searchInput = document.getElementById("club-search");

if(searchInput){

    searchInput.addEventListener("input", () => {
        const searchText = searchInput.value.toLowerCase();
        const filteredClubs = clubs.filter(item =>
            item.name.toLowerCase().includes(searchText)
        );
        renderClubs(filteredClubs);
    });
}

const sportFilter = document.getElementById("sport-filter");

if (sportFilter) {

    sportFilter.addEventListener("change", () => {

        const selectedSport = sportFilter.value;

        const filteredClubs = clubs.filter(item => {

            if (selectedSport === "all") {
                return true;
            }

            return item.sport.toLowerCase() === selectedSport;

        });

        renderClubs(filteredClubs);

    });

}



/*
clubs.forEach(item => {
    console.log(item.id);
    console.log(item.name);
    console.log(item.city);
    console.log(item.sport);
});


console.log("Name"+clubs[0].name);
console.log("City"+clubs[0].city);
console.log("Sport"+clubs[0].sport);
*/
//console.log(clubs);