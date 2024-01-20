# Custom Branding ThingsBoard

## Replace home title logo
Open file
```text
~\thingsboard\ui-ngx\src\app\shared\components\logo.component.ts
```
Change path to title logo svg file

```text
logo = 'assets/your_title_logo.svg';
```
Recommend a title logo size of around 1300px width and 300px height.

### Making a logo
You can use any application to create an SVG logo file.

I use https://vectr.com/. The free version has ad, but the paid version is very reasonable.

Recommend a title logo size of around 1300px width and 300px height.

Remove XML tag from SVG file (if any):
Open SVG file with any text editor
Remove these two lines if exist
```text
<?xml version="1.0" encoding="UTF-8" standalone="no" ?>
<!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN" "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">
```
Only thing remain should be within the SVG tag
```text
<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" width="1280" height="204" viewBox="0 0 1280 204" xml:space="preserve">
<desc>Created with Fabric.js 5.3.0</desc>
<defs>
</defs>
<g transform="matrix(1 0 0 1 640 102)" id="E9UPLKkwOMQ5iij3uWp3G"  >
	<image></image>
</g>
</svg>
```
## Replace login title logo
Open file
```text
~\thingsboard\ui-ngx\src\app\shared\components\logo.component.ts
```
Change path to title logo svg file

```text
logo = 'assets/your_title_logo.svg';
```

Change the link when user click on logo
Code below go back to base Url instead of thingsboard.io
```text
gotoThingsboard(): void {
    var currentUrl = window.location.href;
    var url = new URL(currentUrl);
    var baseUrl = url.origin;
    window.location.href = baseUrl;
  }
```
## Replace dashboard title logo
Open file
```text
~\thingsboard\ui-ngx\src\app\modules\home\components\dashboard-page\dashboard-page.component.ts
```
Change path to title logo svg file (as of 1/14/2024 line 262)

```text
logo = 'assets/your_title_logo.svg';
```
## Change page title and icon
Open file
```text
~\thingsboard\ui-ngx\src\index.html
```
As of (1/14/2024 line 22), change title to your site title
```text
<title>Your Page Title</title>
```

### icon
Open file
```text
~\thingsboard\ui-ngx\src\index.html
```
As of (1/14/2024 line 26), change icon to your site icon
```text
<link rel="icon" type="image/x-icon" href="your_site_icon.ico">
```
Open file
```text
~\thingsboard\ui-ngx\angular.json
```
As of (1/14/2024 line 30), change icon to your site icon
```text
"src/your_site_icon.ico",
```
## Replace Copyright notice
Open file
```text
~\thingsboard\ui-ngx\src\app\shared\components\footer.component.html
```
As of (1/14/2024 line 19), change copyright
```text
 <small>
    Copyright © {{year}} your_site_name. Powered by <a href="https://thingsboard.io" target="_blank">ThingsBoard</a>. Open Source by <a href="https://thingsboard.io" target="_blank">The ThingsBoard Authors</a>.
</small>
```
## Change App Title
Open file
```text
~\thingsboard\ui-ngx\src\environments\environment.prod.ts
```
As of (1/14/2024 line 18), change app title
```text
appTitle: 'Your App Title',
```

## Replace Company Name - 2FA Verification Code Message
Open file
```text
~\thingsboard\application\src\main\resources\templates\2fa.verification.code.ftl
```
As of (1/14/2024), replace the Tinkgsboard with your Company Name
```text
Line 99
Line 107
```

## Replace Company Name - Account Activiated
Open file
```text
~\thingsboard\application\src\main\resources\templates\account.activated.ftl
```
As of (1/14/2024), replace the Tinkgsboard with your Company Name
```text
Line 23
Line 86
Line 91
Line 96
Line 106
Line 114
```
## Replace Company Name - Account Lockout
Open file
```text
~\thingsboard\application\src\main\resources\templates\account.lockout.ftl
```
As of (1/14/2024), replace the Tinkgsboard with your Company Name
```text
Line 23
Line 86
Line 91
Line 96
Line 104
```

## Replace Company Name - Activation
Open file
```text
~\thingsboard\application\src\main\resources\templates\activation.ftl
```
As of (1/14/2024), replace the Tinkgsboard with your Company Name
```text
Line 23
Line 86
Line 106
Line 114
```

## Replace Company Name - Password was reset
Open file
```text
~\thingsboard\application\src\main\resources\templates\password.was.reset.ftl
```
As of (1/14/2024), replace the Tinkgsboard with your Company Name
```text
Line 23
Line 86
Line 91
Line 96
Line 106
Line 114
```

## Replace Company Name - Reset password
Open file
```text
~\thingsboard\application\src\main\resources\templates\reset.password.ftl
```
As of (1/14/2024), replace the Tinkgsboard with your Company Name
```text
Line 23
Line 91
Line 106
Line 114
```

## Replace Company Name - State disabled
Open file
```text
~\thingsboard\application\src\main\resources\templates\state.disabled.ftl
```
As of (1/14/2024), replace the Tinkgsboard with your Company Name
```text
Line 25
Line 113 - need to change link to head.png
Line 116
Line 120 - need to change the link to alarm.png
Line 121
Line 129
Line 140
```

## Replace Company Name - State enabled
Open file
```text
~\thingsboard\application\src\main\resources\templates\state.enabled.ftl
```
As of (1/14/2024), replace the Tinkgsboard with your Company Name
```text
Line 25
Line 113 - need to change link to head.png
Line 116
Line 120 - need to change the link to alarm.png
Line 121
Line 126
Line 137
```

## Replace Company Name - State warning
Open file
```text
~\thingsboard\application\src\main\resources\templates\state.warning.ftl
```
As of (1/14/2024), replace the Tinkgsboard with your Company Name
```text
Line 25
Line 113 - need to change link to head.png
Line 116
Line 120 - need to change the link to alarm.png
Line 121
Line 129
Line 140
```

## Replace Company Name - Test
Open file
```text
~\thingsboard\application\src\main\resources\templates\test.ftl
```
As of (1/14/2024), replace the Tinkgsboard with your Company Name
```text
Line 23
Line 86
Line 96
Line 104
```

## Modify doc links
Open file
```text
~\thingsboard\ui-ngx\src\app\modules\home\components\widget\lib\home-page\doc-links-widget.component.ts
```
Edit as needed
