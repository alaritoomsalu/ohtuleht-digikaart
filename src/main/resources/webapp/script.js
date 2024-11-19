async function sendWish() {
    try {
        const response = await fetch("/count-wish", {
            method: "POST",
        });

        if (!response.ok) {
            alert("Midagi läks valesti!");
            return;
        }

        gtag("event", "wish_sent", {
            event_category: "Engagement",
            event_label: "Wish Submission",
            value: 1,
        });

        // Clear textarea and switch cards
        document.querySelector(".input-field").value = "";
        document.querySelector(".first-card").classList.add("hidden");
        document.querySelector(".second-card").classList.remove("hidden");

    } catch (error) {
        console.error("Viga soovi saatmisel:", error);
        alert("Serveriga ühendamine ebaõnnestus!");
    }
}