export default async function getEmployers() {
  try {
    let response;
    response = await fetch("/employers/all", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });
    const employers = await response.json();
    return employers;
  } catch (err) {
    console.log(err);
  }
}
