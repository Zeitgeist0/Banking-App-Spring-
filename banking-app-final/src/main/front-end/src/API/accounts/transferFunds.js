export default async function transferFunds(
  originAccountNumber,
  destinationAccountNumber,
  funds
) {
  try {
    const res = await fetch(`http://localhost:9000/accounts/transferFunds`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        originAccountNumber,
        destinationAccountNumber,
        funds,
      }),
    });
    return res.json();
  } catch (e) {
    console.log(e);
  }
}
