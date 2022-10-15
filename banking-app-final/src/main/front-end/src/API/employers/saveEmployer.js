export default async function saveEmployer(name, address, customerIds) {
  try {
    const res = await fetch(`/employers`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name,
        address,
        customerIds,
      }),
    });
    return res.json();
  } catch (e) {
    console.log(e);
  }
}
