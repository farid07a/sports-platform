console.log("Sport DZ java Script is working");

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



    //console.log("clubGrid is null"); // Runs if it was not found
    //console.log("passe1");





function renderClubs() {
    const clubGrid = document.querySelector(".clubs-grid")


if (clubGrid) {
  console.log(clubGrid); // Runs if the element exists
   console.log(clubGrid);


    clubs.forEach(item => {
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

}

function renderClubDetails(){
    
    const params = new URLSearchParams(window.location.search);

    const clubId = Number(params.get("id"));

    const selectedClub = clubs.find(item => item.id === clubId);

    console.log(selectedClub);


    const clubName = document.getElementById("club-name");
    const clubCity = document.getElementById("club-city");
    const clubSport = document.getElementById("club-sport");
    const clubImage = document.getElementById("club-image");

    clubName.textContent = selectedClub.name;
    clubCity.textContent = selectedClub.city;
    clubSport.textContent = selectedClub.sport;
    clubImage.src = selectedClub.image;
    clubImage.alt = selectedClub.name;

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