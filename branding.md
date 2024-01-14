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
You can use any application to create a svg logo file.
I use https://vectr.com/. Free version with add but paid version is very reasonable.
Recomend title logo size around 1300px width and 300 px height.
Remove xml tag from svg file (if any):
Open svg file with any text editor
Remove these two line if exist
```text
<?xml version="1.0" encoding="UTF-8" standalone="no" ?>
<!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN" "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">
```
Only thing remain should be within the <svg ....> </svg> tag
