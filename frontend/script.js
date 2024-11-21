async function sendWish() {
    // Get the value from the textarea
    const wish = document.querySelector(".input-field").value;

    // Check if the user entered a wish
    if (!wish.trim()) {
        alert("Palun kirjuta oma soov!");
        return;
    }

    alert("Loodame, et su soov täitub!");

    /*
    try {
        const response = await fetch("http://localhost:8080/count-wish", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            alert("Midagi läks valesti!");
            return;
        }


        // Clear textarea and switch cards
        document.querySelector(".input-field").value = "";
        document.querySelector(".first-card").classList.add("hidden");
        document.querySelector(".second-card").classList.remove("hidden");

    } catch (error) {
        console.error("Error while sending wish:", error);
        alert("Serveriga ühendamine ebaõnnestus!");
    }
    */

    // Clear textarea and switch cards
    document.querySelector(".input-field").value = "";
    document.querySelector(".first-card").classList.add("hidden");
    document.querySelector(".second-card").classList.remove("hidden");
}