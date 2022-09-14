export default async function createNewCustomer(name, age, email) {
  try {
    const res = await fetch(`http://localhost:9000/customers`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name,
        age,
        email,
      }),
    });
    return res.json();
  } catch (e) {
    console.log(e);
  }
}
