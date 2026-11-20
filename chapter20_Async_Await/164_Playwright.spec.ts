import {test,expect} from '@playwright/test';

test('uses await in playwright test', async ({page}) => {
    await page.goto('https://example.com');
});
