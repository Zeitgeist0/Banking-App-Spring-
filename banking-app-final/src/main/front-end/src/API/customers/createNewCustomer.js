export default async function createNewCustomer(
  name,
  password,
  email,
  age,
  phoneNumber
) {
  try {
    const res = await fetch(`/customers`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name,
        password,
        email,
        age,
        phoneNumber,
      }),
    });
    return res.json();
  } catch (e) {
    console.log(e);
  }
}
