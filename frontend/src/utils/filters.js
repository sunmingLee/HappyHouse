export function numberFormat(x) {
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

export function numberToKorean(number) {
  let inputNumber = number < 0 ? false : number;
  let unitWords = ["", "만", "억", "조", "경"];
  let splitUnit = 10000;
  let splitCount = unitWords.length;
  let resultArray = [];
  let resultString = "";

  for (let i = 0; i < splitCount; i++) {
    let unitResult =
      (inputNumber % Math.pow(splitUnit, i + 1)) / Math.pow(splitUnit, i);
    unitResult = Math.floor(unitResult);
    if (unitResult > 0) {
      resultArray[i] = unitResult;
    }
  }

  for (let i = 0; i < resultArray.length; i++) {
    if (!resultArray[i]) continue;
    resultString =
      String(numberFormat(resultArray[i])) + unitWords[i] + resultString;
  }

  return resultString;
}

export function textLengthOverCut(txt, len, lastTxt) {
  if (len == "" || len == null) {
    // 기본값
    len = 10;
  }
  if (lastTxt == "" || lastTxt == null) {
    // 기본값
    lastTxt = "...";
  }
  if (txt.length > len) {
    txt = txt.substr(0, len) + lastTxt;
  }
  return txt;
}

export function handleNewLine(str) {
  return String(str).replace(/(?:\r\n|\r|\n)/g, "</br>");
}
