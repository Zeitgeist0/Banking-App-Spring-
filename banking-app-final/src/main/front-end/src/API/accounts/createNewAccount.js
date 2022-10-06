export default async function createNewAccount(currency, customerId, balance) {
  try {
    const res = await fetch(`http://localhost:9000/accounts`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        currency,
        customerId,
        balance,
      }),
    });
    return res.json();
  } catch (e) {
    console.log(e);
  }
}
