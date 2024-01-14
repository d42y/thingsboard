# Custom Branding ThingsBoard

## Replace title logo
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
