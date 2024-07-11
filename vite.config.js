import { defineConfig } from 'vite';
import laravel from 'laravel-vite-plugin';

export default defineConfig({
    plugins: [
        laravel({
            input: [
                // css home 
                'resources/css/home/style.css',
                'resources/css/home/responsive.css',
                'resources/js/app.js'
            ],
            refresh: true,
        }),
    ],
});
