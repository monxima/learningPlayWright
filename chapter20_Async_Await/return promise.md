# Ways to Return a Promise in JavaScript

A function "returns a promise" when the caller can use `.then()/.catch()` or `await` on its result.
There are **7 common ways**.

---

## 1. `new Promise()` constructor (manual)

Wrap async work yourself and call `resolve` / `reject`.

```js
function getUser() {
  return new Promise((resolve, reject) => {
    setTimeout(() => resolve("Monxima"), 1000);
  });
}

getUser().then(console.log); // Monxima
```

Use when: wrapping callback-based code (setTimeout, events, old APIs).

---

## 2. `async` function (automatic)

An `async` function **always** returns a promise. Returned value is wrapped in `Promise.resolve`, thrown error becomes a rejection.

```js
async function getUser() {
  return "Monxima"; // same as Promise.resolve("Monxima")
}

async function fail() {
  throw new Error("boom"); // same as Promise.reject(new Error("boom"))
}

getUser().then(console.log);
```

Use when: you want the cleanest syntax, especially with `await` inside.

---

## 3. `Promise.resolve()` / `Promise.reject()` (already-settled)

Shortcut to create a promise that is already fulfilled or rejected.

```js
function getUser() {
  return Promise.resolve("Monxima");
}

function fail() {
  return Promise.reject(new Error("not found"));
}
```

Use when: value is already known (cache hit, mocks, tests).

---

## 4. Return another promise from `.then()` (chaining)

Whatever you return inside `.then()` becomes the next promise. If you return a promise, the chain waits for it.

```js
function getOrder() {
  return fetchUser()                  // promise 1
    .then(user => fetchOrders(user))  // return promise 2
    .then(orders => orders[0]);       // plain value, wrapped automatically
}
```

Use when: sequential async steps.

---

## 5. Return a promise from another API directly (pass-through)

Do not re-wrap. Just return it.

```js
function loadData() {
  return fetch("https://api.example.com/data"); // already a promise
}

// Playwright example
function openPage(page) {
  return page.goto("https://example.com");
}
```

Anti-pattern (needless wrapper):

```js
// BAD
return new Promise(resolve => fetch(url).then(resolve));
// GOOD
return fetch(url);
```

---

## 6. Promise combinators (return many promises as one)

Combine several promises into a single returned promise.

```js
function loadAll() {
  return Promise.all([fetchA(), fetchB(), fetchC()]);       // all succeed, else reject
}

Promise.allSettled([p1, p2]); // never rejects, gives status of each
Promise.race([p1, p2]);       // first to settle wins
Promise.any([p1, p2]);        // first to fulfill wins
```

Use when: running tasks in parallel.

---

## 7. `util.promisify` (convert callback API to promise)

Node helper that turns `(err, result)` callback functions into promise-returning ones.

```js
const { promisify } = require("util");
const fs = require("fs");

const readFile = promisify(fs.readFile);

function load() {
  return readFile("data.txt", "utf8"); // returns a promise
}
```

Modern shortcut: `require("fs/promises")` already returns promises.

---

## Quick comparison

| # | Way | Best for |
|---|-----|----------|
| 1 | `new Promise` | Wrapping callbacks / timers / events |
| 2 | `async` function | Cleanest everyday code |
| 3 | `Promise.resolve/reject` | Known values, tests, mocks |
| 4 | Return inside `.then()` | Sequential chains |
| 5 | Pass-through of existing promise | Avoid extra wrapping |
| 6 | `Promise.all/allSettled/race/any` | Parallel work |
| 7 | `util.promisify` | Old Node callback APIs |

---

## Gotchas

- **Forgetting `return`** inside `.then()` or a function means caller gets `undefined`, not a promise.

  ```js
  function bad() {
    fetch(url); // no return: caller gets undefined
  }
  ```
- In an `async` function, `return promise` and `return await promise` both work; `return await` is needed only inside `try/catch` if you want to catch its rejection there.
- `async` function never returns a plain value: `typeof` result is always a Promise.
- Arrow functions work the same: `const f = () => Promise.resolve(1);` or `const f = async () => 1;`
